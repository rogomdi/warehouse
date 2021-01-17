package com.ikea.warehouse.domain.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.*;

/**
 * Entity that defines the amount of articles to build a part of any product
 *
 * @author robertogomez
 */
@Data
//Required for JMapper
@NoArgsConstructor
public class Composition {

    /**
     * Item. See {@link Article}.
     */
    @JMap
    private Article article;

    /**
     * Amount of articles necessaries
     */
    @JMap
    private int quantity;

}
