package com.inspire12.practice.api.module.user.infrastructure.repository;

import com.inspire12.practice.api.module.user.infrastructure.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByEmail(String email);
}
