package com.ikea.warehouse.application.service;

import com.googlecode.jmapper.JMapper;
import com.ikea.warehouse.application.exception.WarehouseException;
import com.ikea.warehouse.application.model.dto.ArticleDto;
import com.ikea.warehouse.domain.exception.NotFoundException;
import com.ikea.warehouse.domain.model.Article;
import com.ikea.warehouse.domain.service.ArticleService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ApplicationArticleServiceTest {
    private final ArticleService articleService = mock(ArticleService.class);
    private final ApplicationArticleService applicationArticleService = new ApplicationArticleService(articleService);
    private static final Article TEST_ARTICLE = buildArticle("test_article", 1);
    private static final ArticleDto TEST_ARTICLE_DTO = new JMapper<>(ArticleDto.class, Article.class).getDestination(TEST_ARTICLE);

    private static Article buildArticle(String name, long stock){
        Article article = new Article();
        article.setStock(stock);
        article.setName(name);
        return article;
    }

    @Test
    void getArticle() throws NotFoundException {
        when(articleService.getById(eq(TEST_ARTICLE.getId()))).thenReturn(TEST_ARTICLE);
        ArticleDto article = applicationArticleService.getArticle(TEST_ARTICLE.getId());
        assertEquals(TEST_ARTICLE.getStock(), article.getStock());
        assertEquals(TEST_ARTICLE.getId(), article.getId());
        assertEquals(TEST_ARTICLE.getName(), article.getName());
    }

    @Test
    void getArticleNotFound() throws NotFoundException {
        when(articleService.getById(eq(TEST_ARTICLE.getId()))).thenThrow(new NotFoundException(""));
        long id = TEST_ARTICLE.getId();
        assertThrows(WarehouseException.class, () -> applicationArticleService.getArticle(id));
    }

    @Test
    void store() {
        when(articleService.save(any(Article.class))).then(returnsFirstArg());
        applicationArticleService.store(List.of(TEST_ARTICLE_DTO));
        verify(articleService).save(any(Article.class));
    }
}