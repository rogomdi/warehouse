package com.ikea.warehouse.application.model.dto;

import com.googlecode.jmapper.annotations.JMap;
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
