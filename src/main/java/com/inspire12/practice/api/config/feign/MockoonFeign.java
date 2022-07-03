package com.inspire12.practice.api.config.feign;

import com.inspire12.practice.api.domain.coupon.CouponIssueInfo;
import com.inspire12.practice.api.domain.coupon.CouponIssueStatusInfo;
import com.inspire12.practice.api.domain.product.ProductListResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mockoon", url = "${api-url:dummyjson.com}")
public interface MockoonFeign {
//    https://dummyjson.com/products/search?q=Laptop
    @GetMapping("/products/search")
    ProductListResponse infos(@RequestParam("q") String query);

    @GetMapping("/coupon/v1/issue/status/{memberNo}")
    CouponIssueStatusInfo issueStatusInfo(@PathVariable(value = "memberNo") String memberNo,
        @RequestParam(value = "couponNo") Long couponNo);

    @GetMapping("/coupon/v1/issued/status/{memberNo}")
    List<CouponIssueInfo> issueInfos(@PathVariable("memberNo") String memberNo,
        @RequestParam("couponNos") List<Long> couponNos);

}
