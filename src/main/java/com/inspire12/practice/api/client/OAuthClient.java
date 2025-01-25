package com.inspire12.practice.api.client;

import com.inspire12.practice.api.config.web.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oAuthClient", url = "${controller.url}", configuration = FeignClientConfig.class)
public interface OAuthClient {

    @GetMapping(value = "${controller.oauth.approval-key-reset}", consumes = "application/json")
    String resetApprovalKey(@RequestParam String accountId, @RequestParam String accountType);

    @GetMapping(value = "${controller.oauth.token-key-reset}", consumes = "application/json")
    String resetTokenKey(@RequestParam String accountId, @RequestParam String accountType);

}
