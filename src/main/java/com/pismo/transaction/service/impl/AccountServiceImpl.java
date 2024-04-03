package com.pismo.transaction.service.impl;

import com.pismo.transaction.dataAccess.AccountDataAccess;
import com.pismo.transaction.dto.request.CreateAccountReqDTO;
import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.entity.AccountEntity;
import com.pismo.transaction.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

  private final AccountDataAccess accountDataAccess;

  public AccountDTO create(CreateAccountReqDTO createAccountReqDTO) throws Exception {
    log.info("Request for create account {}", createAccountReqDTO);
    AccountEntity accountEntity = accountDataAccess.getByDocumentNumber(createAccountReqDTO.getDocumentNumber());
    if(accountEntity != null){
      throw new Exception("Account already exists");
    }
    accountEntity = accountDataAccess.create(AccountEntity.builder()
            .documentNumber(createAccountReqDTO.getDocumentNumber())
            .name(createAccountReqDTO.getName())
        .build());

    // Convert the account entity to DTO and return it
    return accountEntity.toDto();
  }
}
