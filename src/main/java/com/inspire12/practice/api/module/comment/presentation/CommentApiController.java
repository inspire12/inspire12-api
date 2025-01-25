package com.inspire12.practice.api.module.comment.presentation;

import com.inspire12.practice.api.module.comment.application.CommentUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@RestController
public class CommentApiController {
    private final CommentUsecase commentService;

}
