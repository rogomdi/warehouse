package com.ikea.warehouse.application.model.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Dto to handle the {@link com.ikea.warehouse.domain.model.Product} on the application layer
 *
 * @author robertogomez
 */
@Data
//Required for JMapper
@NoArgsConstructor
public class ProductDto {

    @JMap
    private UUID id = UUID.randomUUID();

    @JMap
    private String name;

    @JMap
    private List<CompositionDto> composition;

    @JMap
    private double price;
}
