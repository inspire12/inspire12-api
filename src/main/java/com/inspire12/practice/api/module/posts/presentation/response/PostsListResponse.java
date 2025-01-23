package com.inspire12.practice.api.module.posts.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostsListResponse {
    private List<PostResponse> postResponses;
}
