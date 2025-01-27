package com.inspire12.practice.api.module.posts.infrastructure.jparepository;

import com.inspire12.practice.api.module.posts.infrastructure.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p from PostEntity p where p.userId = :userId ORDER BY p.id DESC")
    Slice<PostEntity> findAllDesc(Pageable pageable, Long userId);

}
