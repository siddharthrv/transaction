package com.pismo.transaction.controller;

import com.pismo.transaction.constants.ApiSpecConstants;
import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.dto.request.CreateAccountReqDTO;
import com.pismo.transaction.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
@Tag(name = "Account", description = ApiSpecConstants.ACCOUNT_DESC)
public class AccountController {

  private final AccountService accountService;
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = ApiSpecConstants.CREATE_ACCOUNT_SUMMARY, description = ApiSpecConstants.CREATE_ACCOUNT_DESC)
  public ResponseEntity<AccountDTO> create(@Valid @RequestBody CreateAccountReqDTO request) throws Exception {
    return new ResponseEntity<>(accountService.create(request), HttpStatus.OK);
  }

  @GetMapping(value = "/{accountId}")
  @Operation(summary = ApiSpecConstants.RETRIEVE_ACCOUNT_SUMMARY, description = ApiSpecConstants.RETRIEVE_ACCOUNT_DESC)
  public ResponseEntity<AccountDTO> retrieveAccountByAccountId(@PathVariable("accountId") Long accountId) throws Exception {
    return new ResponseEntity<>(accountService.retrieveAccountByAccountId(accountId), HttpStatus.OK);
  }

}
