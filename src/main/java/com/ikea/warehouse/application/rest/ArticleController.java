package com.ikea.warehouse.application.rest;

import com.ikea.warehouse.application.model.ArticleDto;
import com.ikea.warehouse.application.model.PostArticleRequest;
import com.ikea.warehouse.application.model.ProductDto;
import com.ikea.warehouse.application.model.RestToDtoMapper;
import com.ikea.warehouse.application.service.ApplicationArticleService;
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
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ApplicationArticleService articleService;
    private final RestToDtoMapper restToDtoMapper;

    @Operation(summary = "Get article by id")
    @GetMapping("/{id}")
    public ArticleDto get(@Parameter(description = "Id to retrieve the article") @PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @Operation(summary = "Store articles")
    @PostMapping
    public ResponseEntity<Void> store(@RequestBody PostArticleRequest postArticleRequest) {
        articleService.store(restToDtoMapper.articleDtoMapper(postArticleRequest));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
