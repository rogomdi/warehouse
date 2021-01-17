package com.ikea.warehouse.domain.repository;

import com.ikea.warehouse.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface that defines the repository to access the stored products
 *
 * @author robertogomez
 */
public interface ProductRepository {

    List<Product> findAll(int page, int size);
    Optional<Product> findByUuid(UUID uuid);
    Product save(Product product);
}
