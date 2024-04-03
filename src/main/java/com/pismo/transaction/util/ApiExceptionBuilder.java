package com.pismo.transaction.util;

import com.pismo.transaction.constants.error.ApiErrors;
import java.text.MessageFormat;
import java.util.List;

public class ApiExceptionBuilder {

    public static ApiException build(ApiErrors errorCode, List<String> errors) {
        return new ApiException(errorCode.getCode(), errorCode.getMessage(), errors);
    }

    public static ApiException build(ApiErrors errorCode) {
        return new ApiException(errorCode.getCode(), errorCode.getMessage());
    }

}