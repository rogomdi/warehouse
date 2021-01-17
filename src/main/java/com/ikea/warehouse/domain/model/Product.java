package com.ikea.warehouse.domain.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Entity that defines a product
 *
 * @author robertogomez
 */
@Data
//Required for JMapper
@NoArgsConstructor
public class Product {

    /**
     * Product identifier
     */
    @JMap
    private UUID id = UUID.randomUUID();

    /**
     * Name of the product
     */
    @JMap
    private String name;

    /**
     * List of articles to build the product
     */
    @JMap
    private List<Composition> composition;

    /**
     * Price for the product
     */
    @JMap
    private double price;

}
