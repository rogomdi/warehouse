package com.ikea.warehouse.application.service;

import com.googlecode.jmapper.JMapper;
import com.ikea.warehouse.application.exception.ApplicationError;
import com.ikea.warehouse.application.exception.WarehouseException;
import com.ikea.warehouse.application.model.dto.ProductDto;
import com.ikea.warehouse.domain.exception.NoStockException;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationProductService {

    private final ProductService productService;
    private final JMapper<ProductDto, Product> productDtoMapper = new JMapper<>(ProductDto.class, Product.class);
    private final JMapper<Product, ProductDto> productMapper = new JMapper<>(Product.class, ProductDto.class);

    public List<ProductDto> getProducts(int page, int size) {
        return productService.getAll(page, size).parallelStream()
                .map(productDtoMapper::getDestination)
                .collect(Collectors.toList());
    }

    public void sell(UUID id) {
        try {
            productService.sell(id);
        } catch (NoStockException e) {
            throw new WarehouseException(ApplicationError.INVALID_OPERATION, e);
        } catch (NotFoundException e) {
            throw new WarehouseException(ApplicationError.INVALID_OPERATION_NOT_FOUND, e);
        }
    }

    public List<UUID> store(List<ProductDto> productDtos) {
        return productDtos.stream().map(productDto -> productService.save(productMapper.getDestination(productDto))).map(Product::getId).collect(Collectors.toList());
    }

}
