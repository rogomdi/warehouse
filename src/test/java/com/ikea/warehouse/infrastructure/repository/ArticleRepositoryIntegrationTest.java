package com.ikea.warehouse.infrastructure.repository;

import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.model.Composition;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import(ArticleRepositoryImpl.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ArticleRepositoryIntegrationTest {

    @Autowired private ArticleRepository articleRepository;
    private static final Article TEST_ARTICLE = buildArticle(1, "test_article", 2);

    private static Article buildArticle(long id, String name, long stock){
        Article article = new Article();
        article.setId(id);
        article.setStock(stock);
        article.setName(name);
        return article;
    }

    @Test
    void findById() {
        Article article = articleRepository.save(TEST_ARTICLE);
        assumeTrue(article != null, "Article must be pre-saved before test");
        Article retrievedArticle = articleRepository.findById(TEST_ARTICLE.getId()).orElse(null);
        assertEquals(article,retrievedArticle, "Article must be equal to the pre-saved article");
    }

    @Test
    void save() {
        Article article = articleRepository.save(TEST_ARTICLE);
        assertNotNull(article, "Article cannot be null after save");
    }

}