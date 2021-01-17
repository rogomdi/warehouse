package com.ikea.warehouse.application.model.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.*;


/**
 * Dto to handle the {@link com.ikea.warehouse.domain.model.Composition} on the application layer
 *
 * @author robertogomez
 */
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
