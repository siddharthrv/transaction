package com.pismo.transaction.entity;

import com.pismo.transaction.constants.EntityConstants;
import com.pismo.transaction.dto.AccountDTO;
import com.pismo.transaction.enums.TxnStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Table(name = EntityConstants.TRANSACTION, uniqueConstraints = @UniqueConstraint(columnNames = "ext_txn_id"))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TransactionEntity extends AbstractEntity{

   @ManyToOne
   @JoinColumn(name = "account_id")
   @NotNull
   private AccountEntity account;

   @ManyToOne
   @JoinColumn(name = "operation_id")
   @NotNull
   private OperationTypeEntity operationType;

   @NotNull
   private Double amount;

   @NotNull
   private String extTxnId;

   @Enumerated(EnumType.STRING)
   @NotNull
   private TxnStatus status;
}
