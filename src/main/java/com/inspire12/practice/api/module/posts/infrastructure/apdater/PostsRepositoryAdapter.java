package com.inspire12.practice.api.module.posts.infrastructure.apdater;


import com.inspire12.practice.api.module.posts.domain.Post;
import com.inspire12.practice.api.module.posts.domain.PostRepository;
import com.inspire12.practice.api.module.posts.domain.mapper.PostMapper;
import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import com.inspire12.practice.api.module.posts.presentation.response.PostsListResponse;
import com.inspire12.practice.api.module.posts.presentation.request.PostsSaveRequest;
import com.inspire12.practice.api.module.posts.presentation.request.PostsUpdateRequest;
import com.inspire12.practice.api.module.posts.infrastructure.entity.PostEntity;
import com.inspire12.practice.api.module.posts.infrastructure.jparepository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsRepositoryAdapter implements PostRepository {
    private final PostJpaRepository postsRepository;

    //    @Transactional(transactionManager = CommunityPostDataSource.TX_MANAGER)
    @Override
    public Long save(PostsSaveRequest requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //    @Transactional(readOnly = true, transactionManager = CommunityPostDataSource.TX_MANAGER)
    @Override
    public Long update(Long id, PostsUpdateRequest requestDto) {
        PostEntity postEntity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postEntity.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    //    @Transactional(readOnly = true, transactionManager = CommunityPostDataSource.TX_MANAGER)
    @Override
    public Post findById(Long id) {
        PostEntity entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return Post.create(entity);
    }


    @Override
    public Slice<Post> findByUserId(Pageable pageable, Long userId) {
        Slice<PostEntity> postsEntities = postsRepository.findAllDesc(pageable, userId);
        Pageable postsPageable = postsEntities.getPageable();
        List<Post> posts = postsEntities.stream().map(Post::create).toList();
        return new PageImpl<>(posts, postsPageable, pageable.getPageSize());
    }

    //    @Transactional(readOnly = true, transactionManager = CommunityPostDataSource.TX_MANAGER)
    public PostsListResponse findAllDesc(Pageable pageRequest, Long userId) {

        List<PostResponse> collect = postsRepository.findAllDesc(pageRequest, userId)
                .stream()
                .map((PostEntity post) -> PostMapper.toResponse(Post.create(post)))
                .collect(Collectors.toList());
        return new PostsListResponse(collect);
    }

}
