package com.pismo.transaction.dataAccess;

import com.pismo.transaction.entity.OperationTypeEntity;
import com.pismo.transaction.repository.OperationTypeRepository;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OperationDataAccess {

  private final OperationTypeRepository operationTypeRepository;


    @Cacheable(value = "operationType", key = "#id", unless = "#result == null")
    public OperationTypeEntity findById(Long id){
      return operationTypeRepository.findById(id).orElse(null);
    }
}
