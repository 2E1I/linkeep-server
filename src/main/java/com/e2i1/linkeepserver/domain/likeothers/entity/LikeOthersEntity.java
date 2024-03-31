package com.e2i1.linkeepserver.domain.likeothers.entity;

import com.e2i1.linkeepserver.common.entity.BaseEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "like_others")
public class LikeOthersEntity extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private CollectionsEntity collection;

    public void setUsersEntity(UsersEntity usersEntity) {
        user = usersEntity;
    }
}

