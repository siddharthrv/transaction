package com.pismo.transaction.service;

import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.dto.request.CreateAccountReqDTO;

public interface AccountService {
  AccountDTO create(CreateAccountReqDTO createAccountReqDTO) throws Exception;
}
