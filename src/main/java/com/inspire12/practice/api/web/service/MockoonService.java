package com.inspire12.practice.api.web.service;

import com.inspire12.practice.api.web.dto.Product;
import java.util.List;

public interface MockoonService {

    List<Product> getProducts(String couponNumbers);
}
