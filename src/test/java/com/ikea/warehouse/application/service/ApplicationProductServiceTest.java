package com.ikea.warehouse.application.service;

import com.ikea.warehouse.application.exception.WarehouseException;
import com.ikea.warehouse.application.model.ProductDto;
import com.ikea.warehouse.domain.exception.NoStockException;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.model.Composition;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApplicationProductServiceTest {

    private final ProductService productService = mock(ProductService.class);
    private final ApplicationProductService applicationProductService = new ApplicationProductService(productService);
    private static final Article TEST_ARTICLE = buildArticle("test_article", 2);
    private static final Composition TEST_COMPOSITION = new Composition(TEST_ARTICLE,2);
    private static final Product TEST_PRODUCT = buildProduct("test_product", Collections.singletonList(TEST_COMPOSITION), 20);
    private static final Article TEST_ARTICLE_NO_STOCK = buildArticle("test_article", 0);
    private static final Composition TEST_COMPOSITION_NO_STOCK = new Composition(TEST_ARTICLE_NO_STOCK,2);
    private static final Product TEST_PRODUCT_NO_STOCK = buildProduct("test_product", Collections.singletonList(TEST_COMPOSITION_NO_STOCK), 20);

    private static Article buildArticle(String name, long stock){
        Article article = new Article();
        article.setStock(stock);
        article.setName(name);
        return article;
    }

    private static Product buildProduct(String name, List<Composition> compositions, double price){
        Product product = new Product();
        product.setName(name);
        product.setComposition(compositions);
        product.setPrice(price);
        return product;
    }

    @Test
    void getProducts() {
        when(productService.getAll(any(Integer.class), any(Integer.class))).thenReturn(Collections.singletonList(TEST_PRODUCT));
        List<ProductDto> products = applicationProductService.getProducts(0, 20);
        assertEquals(TEST_PRODUCT.getId(), products.get(0).getId(), "First product should have the same UUID");
        assertEquals(TEST_PRODUCT.getName(), products.get(0).getName(), "First product should have the same name");
        assertEquals(TEST_PRODUCT.getPrice(), products.get(0).getPrice(), "First product should have the same price");
        assertEquals(1, products.size(), "List should contain only one element");
    }

    @Test
    void sell() throws NoStockException, NotFoundException {
        when(productService.sell((TEST_PRODUCT.getId()))).thenReturn(TEST_PRODUCT_NO_STOCK);
        applicationProductService.sell(TEST_PRODUCT.getId());
    }

    @Test
    void sellNoStock() throws NoStockException, NotFoundException {
        when(productService.sell(TEST_PRODUCT_NO_STOCK.getId())).thenThrow(NoStockException.class);
        UUID id = TEST_PRODUCT_NO_STOCK.getId();
        assertThrows(WarehouseException.class, () -> applicationProductService.sell(id), "Should throw exception if selling product without stock");
    }
}