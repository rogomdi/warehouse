package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NoStockException;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Interface that defines the API for the service to manage the products
 *
 * @author robertogomez
 */
public interface ProductService {
    List<Product> getAll(int page, int size);
    Product sell(UUID uuid) throws NoStockException, NotFoundException;
    Product save(Product product);
}
