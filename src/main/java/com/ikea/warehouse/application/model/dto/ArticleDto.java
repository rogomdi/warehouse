package com.ikea.warehouse.application.model.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
