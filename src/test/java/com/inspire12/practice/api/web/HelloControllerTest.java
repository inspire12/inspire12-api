package com.inspire12.practice.api.web;

import com.inspire12.practice.api.web.controller.HelloController;
import com.inspire12.practice.api.web.service.MockoonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = HelloController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc // mock mvc autowired
@EnableWebMvc // json
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MockoonService mockoonService;

    @WithMockUser(roles = "USER")
    @Test
    void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/api/v1/hello?q={}","Laptop"))
                .andExpect(status().isOk());
//                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/api/v1/hello/dto")
                        .contentType("application/json")
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }


    @Test
    void hello() {
    }

    @Test
    void helloDto() {
    }
}