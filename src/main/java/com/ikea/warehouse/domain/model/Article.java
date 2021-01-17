package com.ikea.warehouse.domain.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//Required for JMapper
@NoArgsConstructor
public class Article {

    @JMap
    private long id;

    @JMap
    private String name;

    @JMap
    private long stock;
}
