package com.ikea.warehouse.infrastructure.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
public class CompositionEntity {

    @JMap("article")
    @DBRef
    private ArticleEntity article;

    @JMap
    private int quantity;

}
