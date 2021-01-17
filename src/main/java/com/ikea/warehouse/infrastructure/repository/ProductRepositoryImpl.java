package com.ikea.warehouse.infrastructure.repository;

import com.googlecode.jmapper.JMapper;
import com.ikea.warehouse.application.model.dto.ProductDto;
import com.ikea.warehouse.domain.exception.NoStockException;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.repository.ProductRepository;
import com.ikea.warehouse.infrastructure.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interfaces that implements {@link ProductRepository} to store and get the products
 *
 * @author robertogomez
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductSpringJpaRepository productSpringJpaRepository;
    private final JMapper<Product, ProductEntity> productMapper = new JMapper<>(Product.class, ProductEntity.class);
    private final JMapper<ProductEntity, Product> productEntityMapper = new JMapper<>(ProductEntity.class, Product.class);

    /**
     * Gets a list of products
     *
     * @param page Page of products to retrieve
     * @param size Size of the page
     * @return {@link List} of {@link ProductDto}
     */
    @Override
    public List<Product> findAll(int page, int size) {
        return this.productSpringJpaRepository.findAll(PageRequest.of(page, size)).map(productMapper::getDestination).getContent();
    }

    /**
     * Finds a product by its UUID
     *
     * @param uuid Identifier of the product to find
     */
    @Override
    public Optional<Product> findByUuid(UUID uuid) {
        return this.productSpringJpaRepository.findById(uuid).map(productMapper::getDestination);
    }

    /**
     * Saves a product
     *
     * @param product product to save
     */
    @Override
    public Product save(Product product) {
        return productMapper
                .getDestination(this.productSpringJpaRepository
                        .save(productEntityMapper.getDestination(product)));
    }

}
