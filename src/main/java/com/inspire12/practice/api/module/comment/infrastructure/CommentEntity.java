package com.inspire12.practice.api.module.comment.infrastructure;

import com.inspire12.practice.api.config.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    Long id;
}
