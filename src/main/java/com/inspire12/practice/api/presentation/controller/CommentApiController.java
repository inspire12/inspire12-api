package com.inspire12.practice.api.presentation.controller;

import com.inspire12.practice.api.domain.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@RestController
public class CommentApiController {
    private final CommentService commentService;

}
