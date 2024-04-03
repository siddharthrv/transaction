package com.pismo.transaction.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountReqDTO {
   private String documentNumber;
   private String name;
}
