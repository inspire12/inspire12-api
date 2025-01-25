package com.inspire12.practice.api.module.posts;

import com.inspire12.practice.api.module.posts.application.PostUsecase;
import com.inspire12.practice.api.module.posts.presentation.response.PostsListResponse;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostUsecase postUsecase;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequest request) {
        return postUsecase.savePost(request);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,
                         @RequestBody PostsUpdateRequest requestDto) {
        return postUsecase.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponse findById(@PathVariable Long id) {
        return postUsecase.getPostResponse(id);
    }

    @GetMapping("/api/v1/posts")
    public PostsListResponse findAll(@PageableDefault Pageable pageRequest, @RequestParam Long userId) {
        if (pageRequest == null) {
            pageRequest = PageRequest.of(0, 10);
        }
        return postUsecase.getPostListResponse(pageRequest, userId);
    }

}
