package com.pismo.transaction.repository;

import com.pismo.transaction.entity.TransactionEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
  TransactionEntity findByExtTxnId(String extTxnId);

  List<TransactionEntity> findByAccountIdAndBalanceLessThan(Long accountId, Double lessThan);
}