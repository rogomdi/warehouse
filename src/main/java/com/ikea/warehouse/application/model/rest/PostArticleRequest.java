package com.ikea.warehouse.application.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dto to model the upload of articles through the API
 *
 * @author robertogomez
 */
@Data
@NoArgsConstructor
public class PostArticleRequest {

    @JsonProperty("inventory")
    private List<ArticleRequest> articles;

    @Data
    @NoArgsConstructor
    public static class ArticleRequest {

        @JsonProperty("art_id")
        @Schema(description = "Id of the article")
        private long id;

        @Schema(description = "Name of the article")
        private String name;

        @Schema(description = "Stock of the article")
        private long stock;

    }

}
