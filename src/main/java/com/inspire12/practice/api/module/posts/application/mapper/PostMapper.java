package com.inspire12.practice.api.module.posts.application.mapper;

import com.inspire12.practice.api.module.posts.domain.Post;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.posts.presentation.response.PostsListResponse;

import java.util.List;

public class PostMapper {
    public static PostResponse toResponse(Post post) {
        return new PostResponse(post);
    }

    public static PostsListResponse toListResponse(List<Post> posts) {

        return new PostsListResponse(posts.stream().map(PostMapper::toResponse).toList());
    }
}
