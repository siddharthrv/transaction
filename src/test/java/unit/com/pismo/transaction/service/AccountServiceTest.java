package unit.com.pismo.transaction.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.pismo.transaction.constants.error.ApiErrors;
import com.pismo.transaction.dataAccess.AccountDataAccess;
import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.dto.request.CreateAccountReqDTO;
import com.pismo.transaction.entity.AccountEntity;
import com.pismo.transaction.service.impl.AccountServiceImpl;
import com.pismo.transaction.util.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @Mock
  private AccountDataAccess accountDataAccess;

  @InjectMocks
  private AccountServiceImpl accountService;


  @Test
  @DisplayName("Should throw account already exists error if account with document number already exists")
  void shouldThrowAccountAlreadyExistsException() {
    when(accountDataAccess.getByDocumentNumber(any())).thenReturn(AccountEntity.builder().build());
    ApiException exception = assertThrows(ApiException.class, () -> accountService.create(CreateAccountReqDTO.builder().build()));
    assertEquals(ApiErrors.ACCOUNT_ALREADY_REGISTERED.getCode(), exception.getErrorCode());
  }

  @Test
  @DisplayName("Should throw account already exists error if account with document number already exists")
  void shouldCreateAccount() throws ApiException {
    AccountEntity accountEntity = AccountEntity.builder()
        .name("test")
        .documentNumber("testDoc")
        .id(1L)
        .build();
    when(accountDataAccess.getByDocumentNumber(accountEntity.getDocumentNumber())).thenReturn(null);
    when(accountDataAccess.create(any())).thenReturn(accountEntity);
    AccountDTO accountDTO = accountService.create(CreateAccountReqDTO
        .builder()
        .documentNumber(accountEntity.getDocumentNumber())
        .name(accountEntity.getName())
        .build());

    assertEquals(accountDTO.getDocumentNumber(), accountEntity.getDocumentNumber());
    assertEquals(accountDTO.getName(), accountEntity.getName());
    assertEquals(accountDTO.getId(), accountEntity.getId());
  }

  @Test
  @DisplayName("Should throw account not exists error if account not found")
  void shouldThrowAccountNotFoundException() {
    when(accountDataAccess.getById(1L)).thenReturn(null);
    ApiException exception = assertThrows(ApiException.class, () -> accountService.retrieveAccountByAccountId(1L));
    assertEquals(ApiErrors.ACCOUNT_NOT_FOUND.getCode(), exception.getErrorCode());
  }


  @Test
  @DisplayName("Should return account if account is found")
  void shouldReturnAccount() throws ApiException {
    AccountEntity accountEntity = AccountEntity.builder()
        .name("test")
        .documentNumber("testDoc")
        .id(1L)
        .build();
    when(accountDataAccess.getById(1L)).thenReturn(accountEntity);
    AccountDTO accountDTO = accountService.retrieveAccountByAccountId(1L);
    assertEquals(accountDTO.getDocumentNumber(), accountEntity.getDocumentNumber());
    assertEquals(accountDTO.getName(), accountEntity.getName());
    assertEquals(accountDTO.getId(), accountEntity.getId());
  }

}
