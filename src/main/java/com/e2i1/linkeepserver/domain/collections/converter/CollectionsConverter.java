package com.e2i1.linkeepserver.domain.collections.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionLinkDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionReqDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionResDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionTitleResDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionUserResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class CollectionsConverter {

    public CollectionUserResDTO toCollectionUserResDTO(CollectionsEntity collection, List<CollectionLinkDTO> linkList, List<String> tagsList){
        CollectionUserResDTO userResDTO = CollectionUserResDTO.builder()
                .title(collection.getTitle())
                .imgUrl(collection.getImgURL())
                .description(collection.getDescription())
                .linkList(linkList)
                .tagList(tagsList)
                .build();
        return userResDTO;
    }

    public CollectionTitleResDTO toCollectionTitleResDTO(CollectionsEntity collection){
        return CollectionTitleResDTO.builder().collectionId(collection.getId()).title(
            collection.getTitle()).build();
    }

    public CollectionResDTO toCollectionResDTO(CollectionsEntity collection){
        return CollectionResDTO.builder().collectionId(collection.getId())
            .numOfLikes(collection.getNumOfLikes())
            .color(collection.getColor())
            .title(collection.getTitle())
            .description(collection.getDescription())
            .imgUrl(collection.getImgURL())
            .createdAt(collection.getCreatedAt())
            .favorite(collection.getFavorite())
            .updatedAt(collection.getUpdateAt())
            .access(collection.getAccess().getDescription()).build();
    }

    public CollectionsEntity toEntity(CollectionReqDTO req) {
        return CollectionsEntity.builder()
            .access(req.getAccess())
            .color(req.getColor())
            .description(req.getDescription())
            .title(req.getTitle())
            .favorite(false)
            .imgURL(req.getImgUrl())
            .build();
    }
}
