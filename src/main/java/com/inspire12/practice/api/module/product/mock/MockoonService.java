package com.inspire12.practice.api.module.product.mock;

import com.inspire12.practice.api.module.product.domain.Product;
import java.util.List;

public interface MockoonService {

    List<Product> getProducts(String couponNumbers);

    List<Product> getHello(String couponNumbers);
}
