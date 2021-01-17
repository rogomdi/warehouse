package com.ikea.warehouse.application.rest;

import com.ikea.warehouse.application.model.dto.ArticleDto;
import com.ikea.warehouse.application.model.rest.PostArticleRequest;
import com.ikea.warehouse.application.model.rest.RestToDtoMapper;
import com.ikea.warehouse.application.service.ApplicationArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
