package com.pismo.transaction.dataAccess;

import com.pismo.transaction.entity.AccountEntity;
import com.pismo.transaction.entity.TransactionEntity;
import com.pismo.transaction.repository.AccountRepository;
import com.pismo.transaction.repository.TransactionRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransactionDataAccess {

  private final TransactionRepository transactionRepository;

  public TransactionEntity create(TransactionEntity transaction){
    return transactionRepository.save(transaction);
  }

  public TransactionEntity getByExtTxnId(String extTxnId){
    return transactionRepository.findByExtTxnId(extTxnId);
  }
}
