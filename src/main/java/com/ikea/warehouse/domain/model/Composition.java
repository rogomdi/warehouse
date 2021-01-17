package com.ikea.warehouse.domain.model;

import com.googlecode.jmapper.annotations.JMap;
import com.ikea.warehouse.application.model.ArticleDto;
import com.ikea.warehouse.infrastructure.model.CompositionEntity;
import lombok.*;

import java.util.List;

@Data
//Required for JMapper
@NoArgsConstructor
@AllArgsConstructor
public class Composition {

    @JMap(attributes = {"articleEntity", "article"})
    private Article article;

    @JMap
    private int quantity;

}
