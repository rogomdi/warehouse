package com.ikea.warehouse.application.model.rest;

import com.ikea.warehouse.application.model.dto.ArticleDto;
import com.ikea.warehouse.application.model.dto.CompositionDto;
import com.ikea.warehouse.application.model.dto.ProductDto;
import com.ikea.warehouse.application.service.ApplicationArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper to transform the POST API models into their corresponding Dtos
 *
 * @author robertogomez
 */
@Component
@RequiredArgsConstructor
public class RestToDtoMapper {

    private final ApplicationArticleService articleService;

    /**
     * Transform the POST Articles request into a List of {@link ArticleDto}
     *
     * @param postArticleRequest Request model
     * @return {@link List} of {@link ArticleDto}
     */
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

    /**
     * Transform the POST Products request into a List of {@link ProductDto}
     *
     * @param productRequest Request model
     * @return {@link List} of {@link ProductDto}
     */
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

    /**
     * Transform the Compositions model in the request into a List of {@link CompositionDto}
     *
     * @param compositions Request model
     * @return {@link List} of {@link CompositionDto}
     */
    private List<CompositionDto> compositionDtoMapper(List<PostProductRequest.CompositionRequest> compositions) {
        return compositions.parallelStream()
                .map(composition -> new CompositionDto(this.articleService.getArticle(composition.getArticleId()), composition.getQuantity()))
                .collect(Collectors.toList());
    }
}
