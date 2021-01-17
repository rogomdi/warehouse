package com.ikea.warehouse.infrastructure.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Composition entity that acts as the MongoDB Dto of the {@link com.ikea.warehouse.domain.model.Composition} domain entity
 *
 * @author robertogomez
 */
@Data
@NoArgsConstructor
public class CompositionEntity {

    @JMap
    @DBRef
    private ArticleEntity article;

    @JMap
    private int quantity;

}
