package com.inspire12.practice.api.module.posts.domain;

import com.inspire12.practice.api.module.posts.domain.mapper.PostContentMapper;
import com.inspire12.practice.api.module.posts.infrastructure.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private List<PostContent> contents;
    private Long userId;
    private String author;

    public static Post create(PostEntity entity) {

        return new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getContents().stream()
                        .map(PostContentMapper::create)
                        .toList(),
                entity.getUserId(),
                entity.getAuthor()

        );
    }
}
