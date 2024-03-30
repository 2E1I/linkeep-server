package com.e2i1.linkeepserver.domain.users.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;
}
