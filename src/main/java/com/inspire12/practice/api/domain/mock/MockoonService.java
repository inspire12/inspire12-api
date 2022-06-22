package com.inspire12.practice.api.domain.mock;

import com.inspire12.practice.api.domain.product.Product;
import java.util.List;

public interface MockoonService {

    List<Product> getProducts(String couponNumbers);

    List<Product> getHello(String couponNumbers);
}
