package com.inspire12.practice.api.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentDomainService commentDomainService;
}
