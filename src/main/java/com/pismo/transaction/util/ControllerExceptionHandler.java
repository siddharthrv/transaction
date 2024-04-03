package com.pismo.transaction.util;

import com.pismo.transaction.constants.error.ApiErrors;
import com.pismo.transaction.constants.error.FieldErrors;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public static ResponseEntity<Object> apiException(ApiException apiException) {
        log.error("Error in response", apiException);
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> handleFieldErrors(List<FieldError> fieldErrors) {
        List<String> errorList = new ArrayList<>();
        fieldErrors.forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String fieldErrorMsg = fieldError.getDefaultMessage();
            if (FieldErrors.MANDATORY_FIELD_MISSING.equals(fieldErrorMsg)) {
                errorList.add(MessageFormat.format(FieldErrors.MANDATORY_FIELD_MISSING_MSG, List.of(fieldName)));
            }
        });
        ApiException exception = null;
        if (!errorList.isEmpty()) {
            exception = ApiExceptionBuilder.build(ApiErrors.INVALID_DATA, errorList);
        }
        log.error("Error in response", exception);
        return ResponseEntityBuilder.buildError(exception, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleFieldErrors(ex.getFieldErrors());
    }

    @ExceptionHandler(value = Exception.class)
    public static ResponseEntity<Object> apiException(Exception exception) {
        log.error("Error in response", exception);
        return ResponseEntityBuilder.buildError(ApiExceptionBuilder.build(ApiErrors.UNEXPECTED_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}





