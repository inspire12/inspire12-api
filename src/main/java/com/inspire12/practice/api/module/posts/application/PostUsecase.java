package com.inspire12.practice.api.module.posts.application;

import com.inspire12.practice.api.module.posts.domain.Post;
import com.inspire12.practice.api.module.posts.domain.PostRepository;
import com.inspire12.practice.api.module.posts.domain.mapper.PostMapper;
import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.posts.presentation.response.PostsListResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostUsecase {

    private final PostRepository postsRepository;

    public PostResponse getPostResponse(Long postId) {
        Post post = postsRepository.findById(postId);

        return PostMapper.toResponse(post);
    }

    public PostsListResponse getPostListResponse(Pageable pageRequest, Long userId) {
        Slice<Post> posts = postsRepository.findByUserId(pageRequest, userId);

        return PostMapper.toListResponse(posts.stream().toList());
    }

    public Long savePost(PostsSaveRequest request) {
        return postsRepository.save(request);
    }

    public Long update(Long id, PostsUpdateRequest request) {
        return postsRepository.update(id, request);
    }
}
