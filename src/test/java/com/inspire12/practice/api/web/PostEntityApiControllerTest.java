package com.inspire12.practice.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire12.practice.api.module.posts.infrastructure.entity.PostEntity;
import com.inspire12.practice.api.module.posts.infrastructure.jparepository.PostJpaRepository;
import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @WebMvcTest의 경우 JPA기능이 작동하지 않는다.
 * Controller와 ControllerAdvice 등 외부 연동과 관련된 부분만 활성화
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostEntityApiControllerTest {

//    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostJpaRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
//                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
//    @WithMockUser(roles = "USER")
    void Posts_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequest requestDto = PostsSaveRequest.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        // then
        List<PostEntity> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
//    @WithMockUser(roles = "USER")
    public void Posts_수정된다() throws Exception {
        //given
        PostEntity savedPostEntity = postsRepository.save(PostEntity.builder()
                .title("title")
//                .content("content")
                .author("author")
                .build());

        Long updateId = savedPostEntity.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequest requestDto = PostsUpdateRequest.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        // when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<PostEntity> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle())
                .isEqualTo(expectedTitle);

    }
}