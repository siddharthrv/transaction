package com.pismo.transaction.constants.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiErrors {
    INVALID_DATA("T_001", "Invalid Data"),
    ACCOUNT_ALREADY_REGISTERED("T_002", "Account already exists"),
    UNEXPECTED_ERROR("T_003", "Unexpected error happened"),
    ACCOUNT_NOT_FOUND("T_004", "Requested account does not exist"),
    DUPLICATE_EXT_TXN_ID("T_005", "Duplicate external transaction id"),
    INVALID_OPERATION_TYPE("T_006", "Invalid operation type");

    private final String code;
    private final String message;
}