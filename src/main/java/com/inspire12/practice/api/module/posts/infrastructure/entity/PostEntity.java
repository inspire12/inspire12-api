package com.inspire12.practice.api.module.posts.infrastructure.entity;

import com.inspire12.practice.api.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

import java.util.List;

@Getter // No Setter
@NoArgsConstructor
@Table(name = "post")
@Entity
public class PostEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

//    private String content;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "postId", referencedColumnName = "id", insertable = false, updatable = false)
    @Exclude
    private List<PostContentEntity> contents;

    private Long userId;
    private String author;

    @Builder
    public PostEntity(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
    }
}
