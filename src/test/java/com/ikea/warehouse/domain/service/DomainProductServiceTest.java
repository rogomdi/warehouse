package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NoStockException;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.model.Composition;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import com.ikea.warehouse.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DomainProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ArticleRepository articleRepository = mock(ArticleRepository.class);
    private final ProductService productService = new DomainProductService(productRepository, articleRepository);
    private static final Article TEST_ARTICLE = buildArticle("test_article", 2);
    private static final Composition TEST_COMPOSITION = new Composition(TEST_ARTICLE, 1);
    private static final Product TEST_PRODUCT = buildProduct("test_product", Collections.singletonList(TEST_COMPOSITION), 20);
    private static final Composition TEST_COMPOSITION_NO_STOCK = new Composition(TEST_ARTICLE, 3);
    private static final Product TEST_PRODUCT_NO_STOCK = buildProduct("test_product", Collections.singletonList(TEST_COMPOSITION_NO_STOCK), 20);

    private static Article buildArticle(String name, long stock) {
        Article article = new Article();
        article.setStock(stock);
        article.setName(name);
        return article;
    }

    private static Product buildProduct(String name, List<Composition> compositions, double price) {
        Product product = new Product();
        product.setName(name);
        product.setComposition(compositions);
        product.setPrice(price);
        return product;
    }

    @Test
    void getAll() {
        when(productRepository.findAll(any(Integer.class), any(Integer.class))).thenReturn(Collections.singletonList(TEST_PRODUCT));
        List<Product> products = productService.getAll(0, 20);
        assertEquals(TEST_PRODUCT, products.get(0), "First product should be the test product");
        assertEquals(1, products.size(), "List should contain only one element");
    }

    @Test
    void sell() throws NoStockException, NotFoundException {
        when(productRepository.save(any())).then(returnsFirstArg());
        when(articleRepository.save(any())).then(returnsFirstArg());
        when(productRepository.findByUuid(eq(TEST_PRODUCT.getId()))).thenReturn(Optional.of(TEST_PRODUCT));
        Product product = productService.sell(TEST_PRODUCT.getId());
        product.getComposition().stream().map(Composition::getArticle).forEach(article -> assertEquals(1, article.getStock()));
    }

    @Test
    void sellNoStock() {
        when(productRepository.save(any())).then(returnsFirstArg());
        when(productRepository.findByUuid(eq(TEST_PRODUCT_NO_STOCK.getId()))).thenReturn(Optional.of(TEST_PRODUCT_NO_STOCK));
        assertThrows(NoStockException.class, () -> productService.sell(TEST_PRODUCT_NO_STOCK.getId()));
    }
}