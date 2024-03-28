package com.e2i1.linkeepserver.domain.collections.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CollectionsEntity {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String imgURL;

    private Access access;

    private String color;

    private byte favorite;

    private Long numOfLikes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
