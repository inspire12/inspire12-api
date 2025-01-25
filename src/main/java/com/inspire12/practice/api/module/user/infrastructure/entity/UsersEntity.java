package com.inspire12.practice.api.module.user.infrastructure.entity;

import com.inspire12.practice.api.config.BaseTimeEntity;
import com.inspire12.practice.api.module.user.domain.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UsersEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public UsersEntity(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public UsersEntity update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}