package com.inspire12.practice.api.module.posts.application;

import com.inspire12.practice.api.module.posts.application.mapper.PostMapper;
import com.inspire12.practice.api.module.posts.application.usecase.PostUseCase;
import com.inspire12.practice.api.module.posts.domain.PostRepository;
import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.posts.presentation.response.PostsListResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService implements PostUseCase {
    private final PostRepository postRepository;

    @Override
    public PostResponse getPostResponse(Long postId) {
        return PostMapper.toResponse(postRepository.findById(postId));
    }

    @Override
    public PostsListResponse getPostListResponse(Pageable pageRequest, Long userId) {
        return PostMapper.toListResponse(postRepository.findByUserId(pageRequest, userId).toList());
    }

    @Override
    public Long savePost(PostsSaveRequest request) {
        return postRepository.save(request);
    }

    @Override
    public Long update(Long id, PostsUpdateRequest request) {
        return postRepository.update(id, request);
    }
}
