package com.pismo.transaction.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum TxnStatus {
  INITIATED, SUCCESS, FAILURE
}
