package com.e2i1.linkeepserver.domain.likeothers.entity;

import com.e2i1.linkeepserver.common.entity.BaseEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "like_others")
public class LikeOthersEntity {

    @EmbeddedId
    private LikeOthersId id;

    @ManyToOne(fetch = LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne(fetch = LAZY)
    @MapsId("collectionId")
    @JoinColumn(name = "collection_id")
    private CollectionsEntity collection;

    public void setUsersEntity(UsersEntity usersEntity) {
        this.user = usersEntity;
        if (this.id == null) {
            this.id = new LikeOthersId();
        }
        this.id.setUserId(usersEntity.getId());
    }

    public void setCollectionsEntity(CollectionsEntity collectionsEntity) {
        this.collection = collectionsEntity;
        if (this.id == null) {
            this.id = new LikeOthersId();
        }
        this.id.setCollectionId(collectionsEntity.getId());
    }

}

