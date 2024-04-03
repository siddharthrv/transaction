package com.pismo.transaction.service;

import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.dto.request.CreateAccountReqDTO;
import com.pismo.transaction.util.ApiException;

public interface AccountService {
  AccountDTO create(CreateAccountReqDTO createAccountReqDTO) throws ApiException;
  AccountDTO retrieveAccountByAccountId(String accountId) throws ApiException;
}
