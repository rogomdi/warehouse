package com.ikea.warehouse.application.model;

import com.googlecode.jmapper.annotations.JMap;
import com.ikea.warehouse.infrastructure.model.ArticleEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
//Required for JMapper
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    @JMap
    private long id;

    @JMap
    private String name;

    @JMap
    private long stock;
}
