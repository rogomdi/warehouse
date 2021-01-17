package com.ikea.warehouse.application.rest;

import com.ikea.warehouse.application.exception.ApplicationError;
import com.ikea.warehouse.application.exception.WarehouseException;
import com.ikea.warehouse.application.model.rest.PostProductRequest;
import com.ikea.warehouse.application.model.dto.ProductDto;
import com.ikea.warehouse.application.model.rest.RestToDtoMapper;
import com.ikea.warehouse.application.service.ApplicationProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ApplicationProductService productService;
    private final RestToDtoMapper restToDtoMapper;

    @Operation(summary = "Get all the products using pagination")
    @GetMapping
    public List<ProductDto> getAll(@Parameter(description = "Page of products requested") @RequestParam(defaultValue = "0") int page, @Parameter(description = "Size of the page") @RequestParam(defaultValue = "20") int size) {
        return productService.getProducts(page, size);
    }

    @Operation(summary = "Sell products")
    @PatchMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> sell(@Parameter(description = "UUID for the product to sell", example = "9a79442b-3540-48ed-9e9d-7cf4f20a4acc") @RequestBody String productId) {
        try {
            productService.sell(UUID.fromString(productId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            throw new WarehouseException(ApplicationError.INVALID_OPERATION, e);
        }

    }

    @Operation(summary = "Store products")
    @PostMapping
    public List<UUID> store(@RequestBody PostProductRequest postProductRequest) {
        return productService.store(restToDtoMapper.productDtoMapper(postProductRequest));
    }
}
