package com.inspire12.practice.api.domain.posts;

import com.inspire12.practice.api.config.datasource.CommunityPostDataSource;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsDomainService {
    private final PostsRepository postsRepository;

    @Transactional(transactionManager = CommunityPostDataSource.TX_MANAGER)
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true, transactionManager = CommunityPostDataSource.TX_MANAGER)
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true, transactionManager = CommunityPostDataSource.TX_MANAGER)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true, transactionManager = CommunityPostDataSource.TX_MANAGER)
    public List<PostsListResponseDto> findAllDesc(Pageable pageRequest) {
        return postsRepository.findAllDesc(pageRequest).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
