package com.inspire12.practice.api.domain.posts;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class PostContent {
    @Id
    private Long id;
    private int pos;
    private Long postId;
    private String content;
}
