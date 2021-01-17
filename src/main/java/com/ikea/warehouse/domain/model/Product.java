package com.ikea.warehouse.domain.model;

import com.googlecode.jmapper.annotations.JMap;
import com.ikea.warehouse.infrastructure.model.ProductEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
//Required for JMapper
@NoArgsConstructor
public class Product {

    @JMap
    private UUID id = UUID.randomUUID();

    @JMap
    private String name;

    @JMap(attributes = {"compositionEntities", "composition"})
    private List<Composition> composition;

    @JMap
    private double price;

}
