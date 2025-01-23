package com.inspire12.practice.api.config.feign;

import com.inspire12.practice.api.module.coupon.domain.Coupon;
import com.inspire12.practice.api.module.coupon.CouponIssueStatusInfo;
import com.inspire12.practice.api.module.product.domain.ProductAggregate;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mockoon", url = "${api-url:dummyjson.com}")
public interface MockoonFeign {
//    https://dummyjson.com/products/search?q=Laptop
    @GetMapping("/products/search")
    ProductAggregate infos(@RequestParam("q") String query);

    @GetMapping("/coupon/v1/issue/status/{memberNo}")
    CouponIssueStatusInfo issueStatusInfo(@PathVariable(value = "memberNo") String memberNo,
        @RequestParam(value = "couponNo") Long couponNo);

    @GetMapping("/coupon/v1/issued/status/{memberNo}")
    List<Coupon> issueInfos(@PathVariable("memberNo") String memberNo,
                            @RequestParam("couponNos") List<Long> couponNos);

}
