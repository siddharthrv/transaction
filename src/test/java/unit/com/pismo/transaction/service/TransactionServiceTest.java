package unit.com.pismo.transaction.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.pismo.transaction.constants.error.ApiErrors;
import com.pismo.transaction.dataAccess.AccountDataAccess;
import com.pismo.transaction.dataAccess.OperationDataAccess;
import com.pismo.transaction.dataAccess.TransactionDataAccess;
import com.pismo.transaction.dto.request.CreateTransactionReqDTO;
import com.pismo.transaction.dto.response.CreateTransactionResDTO;
import com.pismo.transaction.entity.AccountEntity;
import com.pismo.transaction.entity.OperationTypeEntity;
import com.pismo.transaction.entity.TransactionEntity;
import com.pismo.transaction.service.impl.TransactionServiceImpl;
import com.pismo.transaction.util.ApiException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @Mock
  private TransactionDataAccess transactionDataAccess;

  @Mock
  private OperationDataAccess operationDataAccess;

  @Mock
  private AccountDataAccess accountDataAccess;

  @InjectMocks
  private TransactionServiceImpl transactionService;


  @Test
  @DisplayName("Should throw operation type not found if operation type not found in db")
  void shouldThrowOperationNotFoundException() {
    when(operationDataAccess.getById(1L)).thenReturn(null);
    ApiException exception = assertThrows(ApiException.class, () -> transactionService.create(CreateTransactionReqDTO.builder()
            .operationTypeId(1L)
        .build()));
    assertEquals(ApiErrors.INVALID_OPERATION_TYPE.getCode(), exception.getErrorCode());
  }

  @Test
  @DisplayName("Should throw account not found exception if account not found in db")
  void shouldThrowAccountNotFoundException() {
    when(operationDataAccess.getById(1L)).thenReturn(OperationTypeEntity.builder()
        .build());
    when(accountDataAccess.getById(1L)).thenReturn(null);
    ApiException exception = assertThrows(ApiException.class, () -> transactionService.create(CreateTransactionReqDTO.builder()
        .operationTypeId(1L)
        .accountId(1L)
        .build()));
    assertEquals(ApiErrors.ACCOUNT_NOT_FOUND.getCode(), exception.getErrorCode());
  }

  @Test
  @DisplayName("Should throw duplicate external transaction id error if transaction already exists")
  void shouldThrowDuplicateTransactionIdError() {
    when(operationDataAccess.getById(1L)).thenReturn(OperationTypeEntity.builder()
        .build());
    when(accountDataAccess.getById(1L)).thenReturn(AccountEntity.builder().build());
    when(transactionDataAccess.getByExtTxnId("extTxnId1")).thenReturn(TransactionEntity.builder().build());
    ApiException exception = assertThrows(ApiException.class, () -> transactionService.create(CreateTransactionReqDTO.builder()
        .operationTypeId(1L)
        .accountId(1L)
        .extTxnId("extTxnId1")
        .build()));
    assertEquals(ApiErrors.DUPLICATE_EXT_TXN_ID.getCode(), exception.getErrorCode());
  }

  @Test
  @DisplayName("Should create creation and set amount to negative if debit transaction")
  void createTransactionShouldSucceedForDebitTxn() throws ApiException {
    OperationTypeEntity operationType = OperationTypeEntity.builder()
        .isCredit(false)
        .description("Test")
        .build();
    AccountEntity accountEntity = AccountEntity.builder().build();
    TransactionEntity transactionEntity = TransactionEntity.builder()
        .operationType(operationType)
        .account(accountEntity)
        .id(2L)
        .build();
    when(operationDataAccess.getById(1L)).thenReturn(operationType);
    when(accountDataAccess.getById(1L)).thenReturn(AccountEntity.builder().build());
    when(transactionDataAccess.getByExtTxnId("extTxnId1")).thenReturn(null);

    ArgumentCaptor<TransactionEntity> argumentCaptor = ArgumentCaptor.forClass(TransactionEntity.class);
    when(transactionDataAccess.create(argumentCaptor.capture())).thenReturn(transactionEntity);

    CreateTransactionReqDTO createTransactionReqDTO = CreateTransactionReqDTO.builder()
        .operationTypeId(1L)
        .accountId(1L)
        .extTxnId("extTxnId1")
        .amount(100d)
        .build();

    CreateTransactionResDTO createTransactionResDTO = transactionService.create(createTransactionReqDTO);
    assertEquals(createTransactionResDTO.getDescription(), operationType.getDescription());
    assertEquals(createTransactionResDTO.getExtTxnId(), createTransactionReqDTO.getExtTxnId());
    assertEquals(createTransactionResDTO.getDescription(), operationType.getDescription());
    assertEquals(createTransactionResDTO.getIntTxnId(), transactionEntity.getId());
    assertEquals(createTransactionResDTO.getBalance(), -createTransactionReqDTO.getAmount());

    //validate if amount is getting stamped negative
    TransactionEntity capturedArgument = argumentCaptor.getValue();
    assertEquals(capturedArgument.getAmount(), -createTransactionReqDTO.getAmount());
  }


  @Test
  @DisplayName("Should create creation and set amount to positive if credit transaction")
  void createTransactionShouldSucceedForCreditTxn() throws ApiException {
    OperationTypeEntity operationType = OperationTypeEntity.builder()
        .isCredit(true)
        .description("Test")
        .build();
    AccountEntity accountEntity = AccountEntity.builder().build();
    TransactionEntity transactionEntity = TransactionEntity.builder()
        .operationType(operationType)
        .account(accountEntity)
        .id(2L)
        .build();
    when(operationDataAccess.getById(1L)).thenReturn(operationType);
    when(accountDataAccess.getById(1L)).thenReturn(AccountEntity.builder().build());
    when(transactionDataAccess.getByExtTxnId("extTxnId1")).thenReturn(null);

    ArgumentCaptor<TransactionEntity> argumentCaptor = ArgumentCaptor.forClass(TransactionEntity.class);
    when(transactionDataAccess.create(argumentCaptor.capture())).thenReturn(transactionEntity);

    CreateTransactionReqDTO createTransactionReqDTO = CreateTransactionReqDTO.builder()
        .operationTypeId(1L)
        .accountId(1L)
        .extTxnId("extTxnId1")
        .amount(100d)
        .build();

    CreateTransactionResDTO createTransactionResDTO = transactionService.create(createTransactionReqDTO);
    assertEquals(createTransactionResDTO.getDescription(), operationType.getDescription());
    assertEquals(createTransactionResDTO.getExtTxnId(), createTransactionReqDTO.getExtTxnId());
    assertEquals(createTransactionResDTO.getDescription(), operationType.getDescription());
    assertEquals(createTransactionResDTO.getIntTxnId(), transactionEntity.getId());
    assertEquals(createTransactionResDTO.getBalance(), createTransactionReqDTO.getAmount());

    //validate if amount is getting stamped positive
    TransactionEntity capturedArgument = argumentCaptor.getValue();
    assertEquals(capturedArgument.getAmount(), createTransactionReqDTO.getAmount());
  }

  @Test
  @DisplayName("Should create creation and set amount to positive if credit transaction after knocking off one debit txn")
  void createTransactionShouldSucceedForCreditTxnWithPreviousSingleDebitTxn() throws ApiException {
    OperationTypeEntity operationType = OperationTypeEntity.builder()
        .isCredit(true)
        .description("Test")
        .build();
    AccountEntity accountEntity = AccountEntity.builder()
        .id(1L)
        .build();
    TransactionEntity transactionEntity = TransactionEntity.builder()
        .operationType(operationType)
        .account(accountEntity)
        .id(2L)
        .build();
    when(operationDataAccess.getById(1L)).thenReturn(operationType);
    when(accountDataAccess.getById(1L)).thenReturn(accountEntity);
    when(transactionDataAccess.getByExtTxnId("extTxnId1")).thenReturn(null);

    when(transactionDataAccess.fetchDebitTxnsWithBalance(1L)).thenReturn(List.of(
        TransactionEntity.builder()
            .extTxnId("extTxnId2")
            .balance(-50.0)
            .amount(-50.0)
            .operationType(operationType)
            .account(accountEntity)
        .build(),
        TransactionEntity.builder()
            .extTxnId("extTxnId3")
            .balance(-23.5)
            .amount(-23.5)
            .operationType(operationType)
            .account(accountEntity)
            .build(),
        TransactionEntity.builder()
            .extTxnId("extTxnId4")
            .balance(-18.7)
            .amount(-18.7)
            .operationType(operationType)
            .account(accountEntity)
            .build()
    ));

    ArgumentCaptor<TransactionEntity> argumentCaptor = ArgumentCaptor.forClass(TransactionEntity.class);
    when(transactionDataAccess.create(argumentCaptor.capture())).thenReturn(transactionEntity);

    CreateTransactionReqDTO createTransactionReqDTO = CreateTransactionReqDTO.builder()
        .operationTypeId(1L)
        .accountId(1L)
        .extTxnId("extTxnId1")
        .amount(60d)
        .build();

    CreateTransactionResDTO createTransactionResDTO = transactionService.create(createTransactionReqDTO);
    assertEquals(createTransactionResDTO.getDescription(), operationType.getDescription());
    assertEquals(createTransactionResDTO.getExtTxnId(), createTransactionReqDTO.getExtTxnId());
    assertEquals(createTransactionResDTO.getDescription(), operationType.getDescription());
    assertEquals(createTransactionResDTO.getIntTxnId(), transactionEntity.getId());
    assertEquals(createTransactionResDTO.getBalance(), 0d);

    //validate if amount is getting stamped positive
    TransactionEntity capturedArgument = argumentCaptor.getValue();
    assertEquals(capturedArgument.getAmount(), createTransactionReqDTO.getAmount());
  }

}
