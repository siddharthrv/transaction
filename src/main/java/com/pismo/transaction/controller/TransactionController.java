package com.pismo.transaction.controller;

import com.pismo.transaction.dto.request.CreateTransactionReqDTO;
import com.pismo.transaction.dto.response.CreateTransactionResDTO;
import com.pismo.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreateTransactionResDTO> create(@Valid @RequestBody CreateTransactionReqDTO request) throws Exception {
    return new ResponseEntity<>(transactionService.create(request), HttpStatus.OK);
  }
}
