package com.e2i1.linkeepserver.domain.collections.entity;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Access {
    PUBLIC("전체 공개"),
    FRIEND("친구 공개"),
    PRIVATE("나만 보기")
    ;

    private final String description;
}
