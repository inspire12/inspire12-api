package com.inspire12.practice.api.module.posts.application.mapper;

import com.inspire12.practice.api.module.posts.domain.PostContent;
import com.inspire12.practice.api.module.posts.infrastructure.entity.PostContentEntity;

public class PostContentMapper {

    public static PostContent create(PostContentEntity postContentEntity) {
        return new PostContent(
                postContentEntity.getId(),
                postContentEntity.getPos(),
                postContentEntity.getPostId(),
                postContentEntity.getContent()
        );
    }
}
