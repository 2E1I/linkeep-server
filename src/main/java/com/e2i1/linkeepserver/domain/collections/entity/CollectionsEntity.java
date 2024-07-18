package com.e2i1.linkeepserver.domain.collections.entity;

import com.e2i1.linkeepserver.common.entity.DateEntity;
import com.e2i1.linkeepserver.common.entity.StringListToStringConverter;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "collections")
public class CollectionsEntity extends DateEntity {

    @OneToMany(mappedBy = "collection", cascade = ALL)   // linksEntity도 영속성 컨텍스트에 넣어줌
    private List<LinksEntity> linkList;


    @OneToMany(mappedBy = "collection", cascade = ALL)
    private List<TagsEntity> tagList;

    @OneToMany(mappedBy = "collection", cascade = ALL)
    private List<CollaboratorsEntity> collaboratorList;

    @OneToMany(mappedBy = "collection", cascade = ALL)
    private List<LikeOthersEntity> likeOtherList;

    private String title;

    private String description;

    private String imgURL;

    @Enumerated(EnumType.STRING)
    private Access access;


    private int color;

    private Boolean favorite;

    private Long numOfLikes;

    @Version
    private Long version;

    // 연관관계 편의 메서드
    public void addLink(LinksEntity link) {
        linkList.add(link);
        link.setCollection(this);
    }

    public void addTag(TagsEntity tag) {
        tagList.add(tag);
        tag.setCollection(this);
    }

    public void addLikes(){
        this.numOfLikes += 1L;
    }

    public void deleteLikes() {
        this.numOfLikes -=1L;
    }
}
