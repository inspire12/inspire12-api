package com.inspire12.practice.api.module.product.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAggregate {

    private List<Product> products;
    private int total;
    private int skip;
    private int limit;
}
