package com.inspire12.practice.api.web;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@TestPropertySource("classpath:application-test.yml")
@SpringBootTest
public class FrontendControllerTest {
//    @Autowired
//    private TestRestTemplate restTemplate;

    private RestTemplate restTemplate;

    @PostConstruct
    void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void 메인페이지_로딩() {

        //when
//        String body = this.restTemplate.getForObject("/", String.class);
//        //then
//        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
    }

    @Test
    public void test() {
        ResponseEntity<Object> exchange = restTemplate.exchange("https://httpbin.org/get",
                HttpMethod.GET, HttpEntity.EMPTY, Object.class);

        System.out.println(exchange);
    }
}
