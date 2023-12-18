package com.inspire12.practice.api.domain.posts;

import com.inspire12.practice.api.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

import java.util.List;

@Getter // No Setter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    private String content;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "postId", referencedColumnName = "id", insertable = false, updatable = false)
    @Exclude
    private List<PostContent> contents;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void setContents(List<PostContent> contents) {
        this.contents = contents;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
