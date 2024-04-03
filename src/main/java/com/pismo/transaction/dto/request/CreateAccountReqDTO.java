package com.pismo.transaction.dto.request;

import com.pismo.transaction.constants.error.FieldErrors;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountReqDTO {
   @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private String documentNumber;
   @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private String name;
}
