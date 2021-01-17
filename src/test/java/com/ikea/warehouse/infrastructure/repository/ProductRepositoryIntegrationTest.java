package com.ikea.warehouse.infrastructure.repository;

import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.model.Composition;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import com.ikea.warehouse.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import({ProductRepositoryImpl.class, ArticleRepositoryImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductRepositoryIntegrationTest {

    @Autowired private ProductRepository productRepository;
    @Autowired private ArticleRepository articleRepository;
    private static final Article TEST_ARTICLE = buildArticle(1, "test_article", 2);
    private static final Composition TEST_COMPOSITION = new Composition(TEST_ARTICLE, 1);
    private static final Product TEST_PRODUCT = buildProduct("test_product", Collections.singletonList(TEST_COMPOSITION), 20);

    private static Article buildArticle(long id, String name, long stock) {
        Article article = new Article();
        article.setId(id);
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
    void save() {
        articleRepository.save(TEST_ARTICLE);
        Product product = productRepository.save(TEST_PRODUCT);
        assertNotNull(product, "Product cannot be null after save");
        assertEquals(TEST_PRODUCT, product, "Product saved should be the same");
    }

    @Test
    void findAll() {
        articleRepository.save(TEST_ARTICLE);
        Product product = productRepository.save(TEST_PRODUCT);
        assumeTrue(product != null, "Product must be pre-saved before test");
        List<Product> products = productRepository.findAll(0, 20);
        assertEquals(1, products.size(), "Query should contain 1 product");
    }

    @Test
    void findById() {
        articleRepository.save(TEST_ARTICLE);
        Product product = productRepository.save(TEST_PRODUCT);
        assumeTrue(product != null, "Product must be pre-saved before test");
        Product retrievedProduct = productRepository.findByUuid(TEST_PRODUCT.getId()).orElse(null);
        assertEquals(product, retrievedProduct, "Product must be equal to the pre-saved product");
    }

}