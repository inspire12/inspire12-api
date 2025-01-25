package com.inspire12.practice.api.module.comment.infrastructure.adapter;

import com.inspire12.practice.api.module.comment.domain.Comment;
import com.inspire12.practice.api.module.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentRepositoryAdapter implements CommentRepository {

//    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
//        commentJpaRepository.findById(postId);
        return List.of();
    }
}
