package com.inspire12.practice.api.module.posts.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class PostContentEntity {
    @Id
    private Long id;
    private int pos;
    private Long postId;
    private String content;
}
