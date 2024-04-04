package com.pismo.transaction.service;

import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.dto.request.CreateAccountReqDTO;
import com.pismo.transaction.dto.request.CreateTransactionReqDTO;
import com.pismo.transaction.dto.response.CreateTransactionResDTO;
import com.pismo.transaction.util.ApiException;

public interface TransactionService {
  CreateTransactionResDTO create(CreateTransactionReqDTO createTransactionReqDTO) throws ApiException;
}
