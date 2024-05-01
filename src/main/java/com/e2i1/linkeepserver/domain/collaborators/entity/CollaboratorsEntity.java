package com.e2i1.linkeepserver.domain.collaborators.entity;


import com.e2i1.linkeepserver.common.entity.BaseEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Table(name = "collaborators")
public class CollaboratorsEntity extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private CollectionsEntity collection;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void setUsersEntity(UsersEntity usersEntity) {
        user = usersEntity;
    }

    public void setCollection(CollectionsEntity collectionsEntity) {
        collection = collectionsEntity;
    }
}
