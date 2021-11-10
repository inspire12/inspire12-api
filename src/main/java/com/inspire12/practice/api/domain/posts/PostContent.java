package com.inspire12.practice.api.domain.posts;

import javax.persistence.Entity;
import javax.persistence.Id;
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
