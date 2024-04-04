package com.pismo.transaction.dto.request;

import com.pismo.transaction.constants.error.FieldErrors;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class CreateTransactionReqDTO {
   @NotNull(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private Long accountId;

   @NotNull(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private Long operationTypeId;

   @Positive(message = FieldErrors.VALUE_MUST_BE_POSITIVE)
   @NotNull(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private Double amount;

   @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
   @Size(min = 10, message = FieldErrors.MIN_LENGTH_NOT_PRESENT)
   private String extTxnId;
}
