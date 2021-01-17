package com.ikea.warehouse.domain.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity that defines an article in the system
 *
 * @author robertogomez
 */
@Data
//Required for JMapper
@NoArgsConstructor
public class Article {

    /**
     * Article identifier
     */
    @JMap
    private long id;

    /**
     * Article name
     */
    @JMap
    private String name;

    /**
     * Stock of the article
     */
    @JMap
    private long stock;
}
