package com.pismo.transaction.entity;

import com.pismo.transaction.dto.AccountDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "document_number"))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountEntity extends AbstractEntity{

   @NotNull
   private String documentNumber;

   @NotNull
   private String name;

   public AccountDTO toDto(){
      return AccountDTO.builder()
          .documentNumber(documentNumber)
          .id(getId())
          .name(name)
          .build();
   }
}
