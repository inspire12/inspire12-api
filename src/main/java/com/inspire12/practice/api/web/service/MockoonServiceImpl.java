package com.inspire12.practice.api.web.service;

import com.inspire12.practice.api.config.feign.MockoonFeign;
import com.inspire12.practice.api.web.dto.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MockoonServiceImpl implements MockoonService {
    private final MockoonFeign mockoonFeign;

    @Override
    public List<Product> getProducts(String couponNumbers) {
        return mockoonFeign.infos(couponNumbers).getProducts();
    }
}
