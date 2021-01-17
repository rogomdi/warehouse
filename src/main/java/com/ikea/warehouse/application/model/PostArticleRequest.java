package com.ikea.warehouse.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.jmapper.annotations.JMap;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostArticleRequest {

    @JsonProperty("inventory")
    private List<ArticleRequest> articles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
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
