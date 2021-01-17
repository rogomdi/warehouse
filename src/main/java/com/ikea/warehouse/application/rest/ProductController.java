package com.ikea.warehouse.application.rest;

import com.ikea.warehouse.application.model.PostProductRequest;
import com.ikea.warehouse.application.model.ProductDto;
import com.ikea.warehouse.application.model.RestToDtoMapper;
import com.ikea.warehouse.application.service.ApplicationProductService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @PatchMapping
    public ResponseEntity<Void> sell(@Parameter(description = "UUID for the product to sell") @RequestBody UUID productIds) {
        productService.sell(productIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Store products")
    @PostMapping
    public ResponseEntity<Void> store(@RequestBody PostProductRequest postProductRequest) {
        productService.store(restToDtoMapper.productDtoMapper(postProductRequest));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
