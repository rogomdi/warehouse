package com.ikea.warehouse.application.model;

import com.googlecode.jmapper.annotations.JMap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
//Required for JMapper
@NoArgsConstructor
@AllArgsConstructor
public class CompositionDto {

    @JMap
    private ArticleDto article;

    @JMap
    private int quantity;

}
