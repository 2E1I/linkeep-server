package com.e2i1.linkeepserver.domain.collaborators.entity;


import com.e2i1.linkeepserver.common.entity.BaseEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Table(name = "collaborators")
public class CollaboratorsEntity {

    @EmbeddedId
    private CollaboratorsId id;

    @ManyToOne(fetch = LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne(fetch = LAZY)
    @MapsId("collectionId")
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
