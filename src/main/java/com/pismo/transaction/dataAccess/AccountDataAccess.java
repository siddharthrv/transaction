package com.pismo.transaction.dataAccess;

import com.pismo.transaction.entity.AccountEntity;
import com.pismo.transaction.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountDataAccess {

  private final AccountRepository accountRepository;

  public AccountEntity create(AccountEntity accountEntity){
    return accountRepository.save(accountEntity);
  }

  public AccountEntity getByDocumentNumber(String documentNumber){
    return accountRepository.findByDocumentNumber(documentNumber);
  }


  @Cacheable(value = "accountType", key = "#id", unless = "#result == null")
  public AccountEntity getById(Long id){
    return accountRepository.findById(id).orElse(null);
  }
}
