package com.inspire12.practice.api.service.domain.product;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse {

    private List<Product> products;
    private int total;
    private int skip;
    private int limit;
}
