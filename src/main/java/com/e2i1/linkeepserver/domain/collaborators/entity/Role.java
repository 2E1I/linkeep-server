package com.e2i1.linkeepserver.domain.collaborators.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    OWNER("주인"),
    COWORKER("공동 작업자")
    ;
    private final String description;
}
