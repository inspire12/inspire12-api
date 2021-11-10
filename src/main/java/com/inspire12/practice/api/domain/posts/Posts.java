package com.inspire12.practice.api.domain.posts;

import com.inspire12.practice.api.domain.BaseTimeEntity;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter // No Setter
@NoArgsConstructor
@Entity
@ToString(exclude = "contents")
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

    public void setContents(List<PostContent> contents) {
        this.contents = contents;
    }

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
