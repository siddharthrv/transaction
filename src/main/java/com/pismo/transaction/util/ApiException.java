package com.pismo.transaction.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pismo.transaction.constants.ApiSpecConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(value = {"stackTrace", "suppressed", "cause", "localizedMessage"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description= ApiSpecConstants.ERROR_CODE)
    private String errorCode;
    @Schema(description= ApiSpecConstants.ERROR_MESSAGE)
    private String message;
    @Schema(description= ApiSpecConstants.VALIDATION_ERRORS)
    private List<String> errors;

    ApiException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}