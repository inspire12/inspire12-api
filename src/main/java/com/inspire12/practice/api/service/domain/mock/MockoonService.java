package com.inspire12.practice.api.service.domain.mock;

import com.inspire12.practice.api.service.domain.product.Product;

import java.util.List;

public interface MockoonService {

    List<Product> getProducts(String couponNumbers);

    List<Product> getHello(String couponNumbers);
}
