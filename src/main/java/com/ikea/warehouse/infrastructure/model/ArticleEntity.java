package com.ikea.warehouse.infrastructure.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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

    private List<CompositionEntity> compositionEntity;
}
