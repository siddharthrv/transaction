package com.pismo.transaction.dto.request;

import com.pismo.transaction.constants.ApiSpecConstants;
import com.pismo.transaction.constants.error.FieldErrors;
import io.swagger.v3.oas.annotations.media.Schema;
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
   @Schema(description= ApiSpecConstants.DOCUMENT_ID)
   @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private String documentNumber;
   @Schema(description= ApiSpecConstants.NAME)
   @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
   private String name;
}
