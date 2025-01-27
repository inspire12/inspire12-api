package com.inspire12.practice.api.module.posts.application.usecase;

import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.posts.presentation.response.PostsListResponse;
import org.springframework.data.domain.Pageable;

public interface PostUseCase {
    PostResponse getPostResponse(Long postId);

    PostsListResponse getPostListResponse(Pageable pageRequest, Long userId);

    Long savePost(PostsSaveRequest request);

    Long update(Long id, PostsUpdateRequest request);
}
