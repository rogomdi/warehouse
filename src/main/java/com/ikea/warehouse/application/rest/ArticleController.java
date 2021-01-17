package com.ikea.warehouse.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikea.warehouse.application.exception.ApplicationError;
import com.ikea.warehouse.application.exception.WarehouseException;
import com.ikea.warehouse.application.model.dto.ArticleDto;
import com.ikea.warehouse.application.model.rest.PostArticleRequest;
import com.ikea.warehouse.application.model.rest.RestToDtoMapper;
import com.ikea.warehouse.application.service.ApplicationArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller to manage the articles
 *
 * @author robertogomez
 */
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

    @Operation(summary = "Store articles by JSON file")
    @PostMapping(value = "/bulk", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> storeFile(@RequestPart("file") MultipartFile multipartFile) {
        try {
            articleService.store(restToDtoMapper.articleDtoMapper(new ObjectMapper().readValue(multipartFile.getBytes(), PostArticleRequest.class)));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            throw new WarehouseException(ApplicationError.INVALID_OPERATION_INCORRECT_FILE_TYPE, e);
        }
    }

}
