package com.inspire12.practice.api.web;

import com.inspire12.practice.api.domain.posts.Posts;
import com.inspire12.practice.api.domain.posts.PostsRepository;
import com.inspire12.practice.api.domain.posts.PostsService;
import com.inspire12.practice.api.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/flux")
public class WebFluxController {

    private final ServerProperties serverProperties;

    @GetMapping
    private Mono<PostsResponseDto> getPostResponse() {
        int port = serverProperties.getPort();
        WebClient client = WebClient.create("http://localhost:" + port);

        return client.get().uri("/api/v1/posts/" + "1")
                   .retrieve()
                   .bodyToMono(PostsResponseDto.class);
    }

}
