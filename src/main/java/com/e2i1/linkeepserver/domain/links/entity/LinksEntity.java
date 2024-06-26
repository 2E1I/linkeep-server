package com.e2i1.linkeepserver.domain.links.entity;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import com.e2i1.linkeepserver.common.entity.DateEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "links")
public class LinksEntity extends DateEntity implements Serializable {

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

    private Long numOfViews;

    @Version
    private Long version;

    public void updateView() {
        this.numOfViews += 1L;
    }

    public void editLink(String title, String description, String url) {

        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
        if (url != null && !url.isEmpty()) {
            this.url = url;
        }
    }

}