package com.pismo.transaction.dto.request;

import com.pismo.transaction.constants.error.FieldErrors;
import jakarta.validation.constraints.NotBlank;
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
public class CreateAccountReqDTO {
   @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private String documentNumber;
   @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private String name;
}
