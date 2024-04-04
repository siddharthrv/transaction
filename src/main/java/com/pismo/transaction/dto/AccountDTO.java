package com.pismo.transaction.dto;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
  @Schema(description= ApiSpecConstants.ACCOUNT_ID)
  private long id;
  @Schema(description= ApiSpecConstants.NAME)
  private String name;
  @NotBlank(message = FieldErrors.MANDATORY_FIELD_MISSING)
  private String documentNumber;
}
