package com.pismo.transaction.dto.response;


import com.pismo.transaction.enums.CrDr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTransactionResDTO {
   private String extTxnId;
   private Long intTxnId;
   private String description;
   private CrDr txnType;
}
