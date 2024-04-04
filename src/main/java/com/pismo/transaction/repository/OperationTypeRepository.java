package com.pismo.transaction.repository;

import com.pismo.transaction.entity.OperationTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends CrudRepository<OperationTypeEntity, Long> {
}