package com.pismo.transaction.service.impl;

import com.pismo.transaction.constants.error.ApiErrors;
import com.pismo.transaction.dataAccess.AccountDataAccess;
import com.pismo.transaction.dataAccess.OperationDataAccess;
import com.pismo.transaction.dataAccess.TransactionDataAccess;
import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.dto.request.CreateAccountReqDTO;
import com.pismo.transaction.dto.request.CreateTransactionReqDTO;
import com.pismo.transaction.dto.response.CreateTransactionResDTO;
import com.pismo.transaction.entity.AccountEntity;
import com.pismo.transaction.entity.OperationTypeEntity;
import com.pismo.transaction.entity.TransactionEntity;
import com.pismo.transaction.enums.CrDr;
import com.pismo.transaction.enums.TxnStatus;
import com.pismo.transaction.service.AccountService;
import com.pismo.transaction.service.TransactionService;
import com.pismo.transaction.util.ApiException;
import com.pismo.transaction.util.ApiExceptionBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  private final TransactionDataAccess transactionDataAccess;

  private final OperationDataAccess operationDataAccess;

  private final AccountDataAccess accountDataAccess;

  @Override
  public CreateTransactionResDTO create(CreateTransactionReqDTO createTransactionReqDTO) throws ApiException {
    //check if operation type exists
    OperationTypeEntity operationType = operationDataAccess.findById(createTransactionReqDTO.getOperationTypeId());
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

    //create transaction record
    transaction = transactionDataAccess.create(TransactionEntity.builder()
        .extTxnId(createTransactionReqDTO.getExtTxnId())
        .status(TxnStatus.SUCCESS)
        .amount(operationType.getIsCredit() ? createTransactionReqDTO.getAmount() : -createTransactionReqDTO.getAmount())
        .account(accountEntity)
        .operationType(operationType)
        .build());


    return CreateTransactionResDTO.builder()
        .extTxnId(createTransactionReqDTO.getExtTxnId())
        .intTxnId(transaction.getId())
        .txnType(operationType.getIsCredit() ? CrDr.CREDIT: CrDr.DEBIT)
        .description(operationType.getDescription())
        .build();
  }
}