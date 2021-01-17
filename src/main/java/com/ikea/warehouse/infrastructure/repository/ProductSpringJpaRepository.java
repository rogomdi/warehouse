package com.ikea.warehouse.infrastructure.repository;

import com.ikea.warehouse.infrastructure.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductSpringJpaRepository extends MongoRepository<ProductEntity, UUID> {
}
