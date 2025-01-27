package com.inspire12.practice.api.module.posts;

import com.inspire12.practice.api.module.posts.application.usecase.PostUseCase;
import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.posts.presentation.response.PostsListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostUseCase postUseCase;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequest request) {
        return postUseCase.savePost(request);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,
                         @RequestBody PostsUpdateRequest requestDto) {
        return postUseCase.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponse findById(@PathVariable Long id) {
        return postUseCase.getPostResponse(id);
    }

    @GetMapping("/api/v1/posts")
    public PostsListResponse findAll(@PageableDefault Pageable pageRequest, @RequestParam Long userId) {
        if (pageRequest == null) {
            pageRequest = PageRequest.of(0, 10);
        }
        return postUseCase.getPostListResponse(pageRequest, userId);
    }

}
