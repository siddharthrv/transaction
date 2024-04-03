package com.pismo.transaction.repository;

import com.pismo.transaction.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
  AccountEntity findByDocumentNumber(String documentNumber);
}