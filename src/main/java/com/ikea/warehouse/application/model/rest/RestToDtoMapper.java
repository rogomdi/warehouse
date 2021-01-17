package com.ikea.warehouse.application.model.rest;

import com.ikea.warehouse.application.model.dto.ArticleDto;
import com.ikea.warehouse.application.model.dto.CompositionDto;
import com.ikea.warehouse.application.model.dto.ProductDto;
import com.ikea.warehouse.application.service.ApplicationArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestToDtoMapper {

    private final ApplicationArticleService articleService;

    public List<ArticleDto> articleDtoMapper(PostArticleRequest postArticleRequest) {
        return postArticleRequest.getArticles()
                .parallelStream()
                .map(articleRequest -> {
                    ArticleDto articleDto = new ArticleDto();
                    articleDto.setId(articleRequest.getId());
                    articleDto.setName(articleRequest.getName());
                    articleDto.setStock(articleRequest.getStock());
                    return articleDto;
                }).collect(Collectors.toList());
    }

    public List<ProductDto> productDtoMapper(PostProductRequest productRequest) {
        return productRequest.getProducts()
                .parallelStream()
                .map(product -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setComposition(compositionDtoMapper(product.getComposition()));
                    productDto.setName(product.getName());
                    productDto.setPrice(product.getPrice());
                    return productDto;
                }).collect(Collectors.toList());
    }

    private List<CompositionDto> compositionDtoMapper(List<PostProductRequest.CompositionRequest> compositions) {
        return compositions.parallelStream()
                .map(composition -> new CompositionDto(this.articleService.getArticle(composition.getArticleId()), composition.getQuantity()))
                .collect(Collectors.toList());
    }
}
