package com.inspire12.practice.api.module.posts.application.vo;

import com.inspire12.practice.api.module.posts.domain.PostContent;
import com.inspire12.practice.api.module.posts.infrastructure.entity.PostContentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostContentResponse {
    private Long id;
    private int pos;
    private Long postId;
    private String content;

    public PostContentResponse(PostContent postContent) {
        this.id = postContent.getId();
        this.pos = postContent.getPos();
        this.postId = postContent.getPostId();
        this.content = postContent.getContent();
    }

}
