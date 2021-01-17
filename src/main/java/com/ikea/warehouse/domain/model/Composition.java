package com.ikea.warehouse.domain.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.*;

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
