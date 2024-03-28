package com.e2i1.linkeepserver.common.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.io.Serializable;

@Getter
@MappedSuperclass
public class BaseEntity{
    @jakarta.persistence.Id
    @GeneratedValue
    private Long Id;
}
