package com.inspire12.practice.api.module.comment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
}
