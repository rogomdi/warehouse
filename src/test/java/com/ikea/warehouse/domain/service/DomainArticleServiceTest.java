package com.ikea.warehouse.domain.service;

import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.model.Composition;
import com.ikea.warehouse.domain.model.Product;
import com.ikea.warehouse.domain.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DomainArticleServiceTest {

    private final ArticleRepository articleRepository = mock(ArticleRepository.class);
    private final ArticleService articleService = new DomainArticleService(articleRepository);
    private static final Article TEST_ARTICLE = buildArticle("test_article", 2);

    private static Article buildArticle(String name, long stock){
        Article article = new Article();
        article.setStock(stock);
        article.setName(name);
        return article;
    }

    @Test
    void getReturnsArticle() throws NotFoundException {
        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.of(TEST_ARTICLE));
        Article article = articleService.getById(TEST_ARTICLE.getId());
        assertEquals(TEST_ARTICLE, article, "Article should be the test article");
    }

    @Test
    void getReturnsException(){
        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> articleService.getById(TEST_ARTICLE.getId()));
    }

    @Test
    void save(){
        when(articleRepository.save(any())).then(returnsFirstArg());
        Article article = articleService.save(TEST_ARTICLE);
        assertEquals(TEST_ARTICLE, article, "Article should be the test article");
    }

}