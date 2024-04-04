package com.pismo.transaction.entity;

import com.pismo.transaction.constants.EntityConstants;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Table(name = EntityConstants.OPERATION_TYPE)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OperationTypeEntity extends AbstractEntity{
   @NotNull
   private String description;
   @NotNull
   private Boolean isCredit;
}
