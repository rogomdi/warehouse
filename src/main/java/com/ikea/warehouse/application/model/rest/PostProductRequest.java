package com.ikea.warehouse.application.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.jmapper.annotations.JMap;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostProductRequest {

    private List<ProductRequest> products;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRequest {

        @Schema(description = "Name for the product")
        private String name;

        @JsonProperty("contain_articles")
        @ArraySchema(schema = @Schema(description="List of articles to build the product", implementation = CompositionRequest.class))
        private List<CompositionRequest> composition;

        private double price;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompositionRequest {

        @JMap
        @JsonProperty("art_id")
        @Schema(description="Id of the article")
        private long articleId;

        @JMap
        @JsonProperty("amount_of")
        @Schema(description="Quantity of articles necessary to build the product")
        private int quantity;

    }
}
