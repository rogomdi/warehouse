package com.ikea.warehouse.application.model.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto to handle the {@link com.ikea.warehouse.domain.model.Article} on the application layer
 *
 * @author robertogomez
 */
@Data
//Required for JMapper
@NoArgsConstructor
public class ArticleDto {

    @JMap
    private long id;

    @JMap
    private String name;

    @JMap
    private long stock;
}
