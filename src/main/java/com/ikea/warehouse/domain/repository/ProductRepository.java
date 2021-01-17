package com.ikea.warehouse.domain.repository;

import com.ikea.warehouse.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    List<Product> findAll(int page, int size);
    Optional<Product> findByUuid(UUID uuid);
    Product save(Product product);
}
