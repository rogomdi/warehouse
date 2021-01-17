package com.ikea.warehouse.infrastructure.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Article entity that acts as the MongoDB Dto of the {@link com.ikea.warehouse.domain.model.Article} domain entity
 *
 * @author robertogomez
 */
@Data
@NoArgsConstructor
@Document(collection = "article")
public class ArticleEntity {

    @Id
    @JMap
    private long id;

    @JMap
    private String name;

    @JMap
    private long stock;

}
