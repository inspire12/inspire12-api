package com.inspire12.practice.api.presentation.controller.sample;

import com.inspire12.practice.api.domain.posts.PostsResponseDto;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flux")
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

    @Timed
    @GetMapping("/api")
    public Flux<String> getTest() {
        List<String> a = List.of("asdfasdfasdfasdf", "asdfasdfasdf");

        return Flux.create((FluxSink<String> sink) -> {
            sink.onRequest(request -> { // request는 Subscriber가 요청한 데이터 개수
                for (int i = 1; i <= request; i++) {
                    sink.next(a.get(i)); // Flux.generate()의 경우와 달리 한 번에 한 개 이상의 next() 신호 발생 가능
                }
            });
        });
    }

}
