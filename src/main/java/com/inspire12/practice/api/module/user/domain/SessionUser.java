package com.inspire12.practice.api.module.user.domain;

import com.inspire12.practice.api.module.user.infrastructure.entity.UsersEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UsersEntity usersEntity) {
        this.name = usersEntity.getName();
        this.email = usersEntity.getEmail();
        this.picture = usersEntity.getPicture();
    }
}
