package com.inspire12.practice.api.presentation.controller;

import com.inspire12.practice.api.domain.posts.PostsDomainService;
import com.inspire12.practice.api.domain.posts.PostsListResponseDto;
import com.inspire12.practice.api.domain.posts.PostsResponseDto;
import com.inspire12.practice.api.domain.posts.PostsSaveRequestDto;
import com.inspire12.practice.api.domain.posts.PostsUpdateRequestDto;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsDomainService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,
                         @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostsListResponseDto> findAll(@PageableDefault Pageable pageRequest) {
        return postsService.findAllDesc(pageRequest);
    }

}
