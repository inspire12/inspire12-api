package com.inspire12.practice.api.module.posts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostContent {
    private Long id;
    private int pos;
    private Long postId;
    private String content;
}
