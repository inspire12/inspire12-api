package com.inspire12.practice.api.presentation.controller;

import com.inspire12.practice.api.config.measure.TimeChecker;
import com.inspire12.practice.api.domain.mock.HelloResponseDto;
import com.inspire12.practice.api.domain.product.Product;
import com.inspire12.practice.api.domain.mock.MockoonService;
import java.security.MessageDigest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final MockoonService mockoonService;

    @GetMapping("/api/v1/test")
    @TimeChecker
    public String helloTest(@RequestParam("q") String query) {
//        AuthUtil.encodeToHex();
        //        return mockoonService.getProducts(query);
        return "hi";
    }
    @GetMapping("/api/v1/hello")
    @TimeChecker
    public List<Product> hello(@RequestParam("q") String query) {
        return mockoonService.getProducts(query);
    }

    @GetMapping("/api/v1/hello/dto")
    @TimeChecker
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                           @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }

    public static class AuthUtil {
        private static final String SALT_PREFIX = "3nJUB7MO";
        private static final String SALT_SUFFIX = "JHhg3lvy";
        private static final long   FLOOR       = 1000000; // 1000 seconds
        public static String generateKey(int offset) {
            long time = System.currentTimeMillis();
            long flooredTime = time - (time % FLOOR);
            String seedStr = SALT_PREFIX + flooredTime + SALT_SUFFIX;
            return encodeToHex(seedStr);
        }
        private static String encodeToHex(String seedStr) {
            try {
                final MessageDigest digest = MessageDigest.getInstance("SHA-256");
                final byte[] hash = digest.digest(seedStr.getBytes("UTF-8"));
                final StringBuilder hexString = new StringBuilder();
                for (int i = 0; i < hash.length; i++) {
                    final String hex = Integer.toHexString(0xff & hash[i]);
                    if (hex.length() == 1)
                        hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
