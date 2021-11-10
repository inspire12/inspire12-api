package com.inspire12.practice.api.web.dto;

import com.inspire12.practice.api.domain.posts.Posts;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private List<PostContentDto> contents;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.contents = entity.getContents().stream()
            .map(PostContentDto::new).collect(Collectors.toList());
        this.author = entity.getAuthor();
    }
}
