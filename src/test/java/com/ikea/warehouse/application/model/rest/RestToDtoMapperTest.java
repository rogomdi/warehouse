package com.ikea.warehouse.application.model.rest;

import com.ikea.warehouse.application.model.dto.ArticleDto;
import com.ikea.warehouse.application.model.dto.ProductDto;
import com.ikea.warehouse.application.service.ApplicationArticleService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestToDtoMapperTest {

    private final ApplicationArticleService articleService = mock(ApplicationArticleService.class);
    private final RestToDtoMapper restToDtoMapper = new RestToDtoMapper(articleService);

    private ArticleDto buildArticle(){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(1);
        articleDto.setName("test");
        articleDto.setStock(20);
        return articleDto;
    }

    private PostArticleRequest buildArticlesRestModel() {
        PostArticleRequest articleRequestModel = new PostArticleRequest();
        PostArticleRequest.ArticleRequest articleRequest = new PostArticleRequest.ArticleRequest();
        articleRequest.setId(1);
        articleRequest.setName("test");
        articleRequest.setStock(20);
        articleRequestModel.setArticles(Collections.singletonList(articleRequest));
        return articleRequestModel;
    }

    private PostProductRequest buildProductsRestModel() {
        PostProductRequest productRestModel = new PostProductRequest();
        PostProductRequest.CompositionRequest compositionRequest = new PostProductRequest.CompositionRequest();
        PostProductRequest.ProductRequest productRequest = new PostProductRequest.ProductRequest();
        compositionRequest.setArticleId(1);
        compositionRequest.setQuantity(2);
        productRequest.setPrice(20);
        productRequest.setName("test");
        productRequest.setComposition(Collections.singletonList(compositionRequest));
        productRestModel.setProducts(Collections.singletonList(productRequest));
        return productRestModel;
    }

    @Test
    void articleDtoMapper() {
        PostArticleRequest articleRequest = buildArticlesRestModel();
        List<ArticleDto> articleDto = this.restToDtoMapper.articleDtoMapper(articleRequest);
        assertEquals(articleRequest.getArticles().size(), articleDto.size());
        assertEquals(articleRequest.getArticles().get(0).getId(), articleDto.get(0).getId());
        assertEquals(articleRequest.getArticles().get(0).getName(), articleDto.get(0).getName());
        assertEquals(articleRequest.getArticles().get(0).getStock(), articleDto.get(0).getStock());
    }

    @Test
    void productDtoMapper() {
        when(articleService.getArticle(1)).thenReturn(buildArticle());
        PostProductRequest productRequest = buildProductsRestModel();
        List<ProductDto> productDtos = this.restToDtoMapper.productDtoMapper(productRequest);
        assertEquals(productRequest.getProducts().size(), productDtos.size());
        assertEquals(productRequest.getProducts().get(0).getPrice(), productDtos.get(0).getPrice());
        assertEquals(productRequest.getProducts().get(0).getName(), productDtos.get(0).getName());
        assertEquals(productRequest.getProducts().get(0).getComposition().get(0).getArticleId(), productDtos.get(0).getComposition().get(0).getArticle().getId());
        assertEquals(productRequest.getProducts().get(0).getComposition().get(0).getQuantity(), productDtos.get(0).getComposition().get(0).getQuantity());
    }
}