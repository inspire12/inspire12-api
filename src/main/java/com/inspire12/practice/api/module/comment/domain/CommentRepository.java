package com.inspire12.practice.api.module.comment.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository {
    List<Comment> getCommentsByPostId(Long postId);
}
