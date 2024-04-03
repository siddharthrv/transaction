package com.pismo.transaction.util;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Data
public class ResponseEntityBuilder {
    public static ResponseEntity<Object> buildError(ApiException apiException, HttpStatus httpStatus) {
        return new ResponseEntity<>(
            apiException,
            httpStatus
        );
    }
}
