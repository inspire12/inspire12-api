package com.inspire12.practice.api.domain.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comment {
    @Id
    Long id;
}
