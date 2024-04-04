package com.pismo.transaction.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String errorCode;
    private String message;
    private List<String> errors;

    ApiException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}