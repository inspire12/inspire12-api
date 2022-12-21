package com.inspire12.practice.api.service.domain.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostContentDto {
    private Long id;
    private int pos;
    private Long postId;
    private String content;

    public PostContentDto(PostContent postContent) {
        this.id = postContent.getId();
        this.pos = postContent.getPos();
        this.postId = postContent.getPostId();
        this.content = postContent.getContent();
    }
}
