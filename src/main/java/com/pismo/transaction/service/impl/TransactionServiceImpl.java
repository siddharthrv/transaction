package com.pismo.transaction.service.impl;

import com.pismo.transaction.constants.error.ApiErrors;
import com.pismo.transaction.dataAccess.AccountDataAccess;
import com.pismo.transaction.dataAccess.OperationDataAccess;
import com.pismo.transaction.dataAccess.TransactionDataAccess;
import com.pismo.transaction.dto.request.CreateTransactionReqDTO;
import com.pismo.transaction.dto.response.CreateTransactionResDTO;
import com.pismo.transaction.entity.AccountEntity;
import com.pismo.transaction.entity.OperationTypeEntity;
import com.pismo.transaction.entity.TransactionEntity;
import com.pismo.transaction.enums.CrDr;
import com.pismo.transaction.enums.TxnStatus;
import com.pismo.transaction.service.TransactionService;
import com.pismo.transaction.util.ApiException;
import com.pismo.transaction.util.ApiExceptionBuilder;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  private final TransactionDataAccess transactionDataAccess;

  private final OperationDataAccess operationDataAccess;

  private final AccountDataAccess accountDataAccess;

  @Override
  @Transactional
  public CreateTransactionResDTO create(CreateTransactionReqDTO createTransactionReqDTO) throws ApiException {
    //check if operation type exists
    OperationTypeEntity operationType = operationDataAccess.getById(createTransactionReqDTO.getOperationTypeId());
    if(operationType == null){
      throw ApiExceptionBuilder.build(ApiErrors.INVALID_OPERATION_TYPE);
    }

    //check if account exists
    AccountEntity accountEntity = accountDataAccess.getById(createTransactionReqDTO.getAccountId());
    if(accountEntity == null){
      throw ApiExceptionBuilder.build(ApiErrors.ACCOUNT_NOT_FOUND);
    }

    //check if external txn id already exists
    TransactionEntity transaction = transactionDataAccess.getByExtTxnId(createTransactionReqDTO.getExtTxnId());
    if(transaction !=null){
      throw ApiExceptionBuilder.build(ApiErrors.DUPLICATE_EXT_TXN_ID);
    }

    Double amount = operationType.getIsCredit() ? createTransactionReqDTO.getAmount() : -createTransactionReqDTO.getAmount();

    //create transaction record
    transaction = transactionDataAccess.create(TransactionEntity.builder()
        .extTxnId(createTransactionReqDTO.getExtTxnId())
        .status(TxnStatus.SUCCESS)
        .amount(amount)
        .account(accountEntity)
        .operationType(operationType)
        .balance(amount)
        .build());

    double balAmount = amount;

    if(operationType.getIsCredit()){
      List<TransactionEntity> debitTxnsWithBalance = transactionDataAccess.fetchDebitTxnsWithBalance(accountEntity.getId());
      if(ObjectUtils.isNotEmpty(debitTxnsWithBalance)){
          List<TransactionEntity> knockedOffTxns = new ArrayList<>();
          for(TransactionEntity debitTxn: debitTxnsWithBalance){
              double knockOffAmount = Math.min(balAmount, Math.abs(debitTxn.getBalance()));
              debitTxn.setBalance(debitTxn.getBalance() + knockOffAmount);
              knockedOffTxns.add(debitTxn);
              balAmount -= knockOffAmount;
              if(balAmount == 0d){
                break;
              }
          }
          transaction.setBalance(balAmount);
          //adding credit txn to the txns save list
          knockedOffTxns.add(transaction);
          transactionDataAccess.saveAll(knockedOffTxns);
      }
    }

    return CreateTransactionResDTO.builder()
        .extTxnId(createTransactionReqDTO.getExtTxnId())
        .intTxnId(transaction.getId())
        .txnType(operationType.getIsCredit() ? CrDr.CREDIT: CrDr.DEBIT)
        .description(operationType.getDescription())
        .balance(balAmount)
        .build();
  }
}
