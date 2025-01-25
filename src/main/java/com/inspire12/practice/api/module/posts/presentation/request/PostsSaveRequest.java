package com.inspire12.practice.api.module.posts.presentation.request;

import com.inspire12.practice.api.module.posts.infrastructure.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequest {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    public PostEntity toEntity() {
        return PostEntity.builder()
                .title(title)
                .author(author)
                .build();
    }
}