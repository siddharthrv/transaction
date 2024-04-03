package com.pismo.transaction.entity;

import com.pismo.transaction.dto.AccountDTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Table(name = "account")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountEntity extends AbstractEntity{

   private String documentNumber;

   private String name;

   public AccountDTO toDto(){
      return AccountDTO.builder()
          .documentNumber(documentNumber)
          .id(getId())
          .name(name)
          .build();
   }
}
