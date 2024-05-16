package com.e2i1.linkeepserver.domain.token.entity;

import static lombok.AccessLevel.PROTECTED;

import com.e2i1.linkeepserver.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "token_black_list")
public class BlackList extends BaseEntity {

    private String invalidToken;

}
