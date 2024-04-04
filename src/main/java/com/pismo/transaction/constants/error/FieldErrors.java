package com.pismo.transaction.constants.error;

import java.util.HashMap;
import java.util.Map;

public interface FieldErrors {
    String MANDATORY_FIELD_MISSING = "MANDATORY_FIELD_MISSING";
    String VALUE_MUST_BE_POSITIVE = "VALUE_MUST_BE_POSITIVE";

    String MIN_LENGTH_NOT_PRESENT = "MIN_LENGTH_NOT_PRESENT";

    Map<String, String> ERROR_MESSAGES = new HashMap<>() {{
        put(MANDATORY_FIELD_MISSING, "Mandatory Field {0} is Missing");
        put(VALUE_MUST_BE_POSITIVE, "Field {0} must be positive");
        put(MIN_LENGTH_NOT_PRESENT, "Field {0} is less than the minimum length");
    }};
}