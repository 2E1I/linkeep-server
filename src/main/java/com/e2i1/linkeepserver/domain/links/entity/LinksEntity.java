package com.e2i1.linkeepserver.domain.links.entity;

import com.e2i1.linkeepserver.common.entity.DateEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
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
@Table(name = "links")
public class LinksEntity extends DateEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "collection_id")
    private CollectionsEntity collection;

    private String title;
    private String url;
    private String description;
    private String numOfViews;

    public void setCollection(CollectionsEntity collection) {
        this.collection = collection;
    }

}
