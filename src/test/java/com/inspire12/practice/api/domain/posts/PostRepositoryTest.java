package com.inspire12.practice.api.domain.posts;

import com.inspire12.practice.api.module.posts.infrastructure.entity.PostEntity;
import com.inspire12.practice.api.module.posts.infrastructure.jparepository.PostJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PostRepositoryTest {
    @Autowired
    PostJpaRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(PostEntity.builder()
                .title(title)

                .author("jojoldu@gmail.com")
                .build());

        //when
        List<PostEntity> postEntityList = postsRepository.findAll();

        //then
        PostEntity postEntity = postEntityList.get(0);
        assertThat(postEntity.getTitle()).isEqualTo(title);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(PostEntity.builder()
                .title("title")

                .author("author")
                .build());
        // when
        List<PostEntity> postEntityList = postsRepository.findAll();

        // then
        PostEntity postEntity = postEntityList.get(0);

        System.out.println(">>>>>>>> createDate=" + postEntity.getCreatedDate() +
                ", modifiedDate=" + postEntity.getModifiedDate());
        assertThat(postEntity.getCreatedDate()).isAfter(now);
        assertThat(postEntity.getModifiedDate()).isAfter(now);
    }

}