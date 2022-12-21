package com.inspire12.practice.api.service.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentDomainService commentDomainService;
}
