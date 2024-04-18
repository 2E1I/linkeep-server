package com.e2i1.linkeepserver.domain.links.entity;

import com.e2i1.linkeepserver.common.entity.DateEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "links")
public class LinksEntity extends DateEntity {

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private CollectionsEntity collection;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    private String title;
    private String url;
    private String description;

    @Column(nullable = false)
    private Long numOfViews;

    public void updateView() {
        this.numOfViews += 1L;
    }

}
