package com.pismo.transaction.dto.response;


import com.pismo.transaction.constants.ApiSpecConstants;
import com.pismo.transaction.enums.CrDr;
import io.swagger.v3.oas.annotations.media.Schema;
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
   @Schema(description= ApiSpecConstants.INT_TXN_ID)
   private Long intTxnId;
   @Schema(description= ApiSpecConstants.TXN_DESC)
   private String description;
   @Schema(description= ApiSpecConstants.TXNTYPE)
   private CrDr txnType;
   private Double balance;
}
