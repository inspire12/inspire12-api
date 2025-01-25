package com.inspire12.practice.api.module.posts.domain;

import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface PostRepository {

    Long save(PostsSaveRequest request);

    Long update(Long id, PostsUpdateRequest request);

    Post findById(Long id);


    Slice<Post> findByUserId(Pageable pageable, Long userId);
}
