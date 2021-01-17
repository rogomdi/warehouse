package com.ikea.warehouse.infrastructure.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "product")
public class ProductEntity {

    @Id
    @JMap
    private UUID id;

    @JMap
    private String name;

    @JMap
    private double price;

    @JMap("composition")
    @Field(targetType = FieldType.ARRAY)
    private List<CompositionEntity> composition;

}
