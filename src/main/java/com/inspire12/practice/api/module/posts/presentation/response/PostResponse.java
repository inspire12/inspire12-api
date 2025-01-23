package com.inspire12.practice.api.module.posts.presentation.response;

import java.util.List;
import java.util.stream.Collectors;

import com.inspire12.practice.api.module.posts.domain.Post;
import com.inspire12.practice.api.module.posts.domain.vo.PostContentResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private List<PostContentResponse> contents;
    private String author;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents().stream()
            .map(PostContentResponse::new).collect(Collectors.toList());
        this.author = entity.getAuthor();
    }
}
