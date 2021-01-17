package com.ikea.warehouse.infrastructure.repository;

import com.googlecode.jmapper.JMapper;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.repository.ProductRepository;
import com.ikea.warehouse.infrastructure.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductSpringJpaRepository productSpringJpaRepository;
    private final JMapper<Product, ProductEntity> productMapper = new JMapper<>(Product.class, ProductEntity.class);
    private final JMapper<ProductEntity, Product> productEntityMapper = new JMapper<>(ProductEntity.class, Product.class);

    @Override
    public List<Product> findAll(int page, int size) {
        return this.productSpringJpaRepository.findAll(PageRequest.of(page, size)).map(productMapper::getDestination).getContent();
    }

    @Override
    public Optional<Product> findByUuid(UUID uuid) {
        return this.productSpringJpaRepository.findById(uuid).map(productMapper::getDestination);
    }

    @Override
    public Product save(Product product) {
        return productMapper
                .getDestination(this.productSpringJpaRepository
                        .save(productEntityMapper.getDestination(product)));
    }

}
