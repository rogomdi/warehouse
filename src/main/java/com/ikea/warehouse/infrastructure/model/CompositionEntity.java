package com.ikea.warehouse.infrastructure.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionEntity {

    @JMap("article")
    @DBRef
    private ArticleEntity article;

    @JMap
    private int quantity;

}
