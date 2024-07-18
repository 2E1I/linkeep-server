package com.e2i1.linkeepserver.domain.collections.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collaborators.dto.CollaboratorResDTO;
import com.e2i1.linkeepserver.domain.collections.dto.*;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class CollectionsConverter {

    public CollectionUserResDTO toCollectionUserResDTO(CollectionsEntity collection, List<CollectionLinkDTO> linkList, List<String> tagsList, boolean isLike, List<CollaboratorResDTO> collaboratorResList){
        return CollectionUserResDTO.builder()
                .title(collection.getTitle())
                .imgUrl(collection.getImgURL())
                .description(collection.getDescription())
                .linkList(linkList)
                .tagList(tagsList)
                .isLike(isLike)
                .collaboratorList(collaboratorResList)
                .build();
    }

    public CollectionTitleResDTO toCollectionTitleResDTO(CollectionsEntity collection){
        return CollectionTitleResDTO.builder().collectionId(collection.getId()).title(
            collection.getTitle()).build();
    }

    public CollectionResDTO toCollectionResDTO(CollectionsEntity collection,boolean isLike, List<String> tagList, List<CollaboratorResDTO> collaboratorList){
        return CollectionResDTO.builder().collectionId(collection.getId())
            .numOfLikes(collection.getNumOfLikes())
            .color(collection.getColor())
            .title(collection.getTitle())
            .description(collection.getDescription())
            .imgUrl(collection.getImgURL())
            .createdAt(collection.getCreatedAt())
            .isLike(isLike)
            .updatedAt(collection.getUpdateAt())
            .access(collection.getAccess().getDescription())
            .collaboratorList(collaboratorList)
            .tagList(tagList).build();
    }

    public CollectionsEntity toEntity(CollectionReqDTO req,String imgUrl) {
        return CollectionsEntity.builder()
            .access(req.getAccess())
            .color(req.getColor())
            .description(req.getDescription())
            .title(req.getTitle())
            .favorite(false)
            .imgURL(imgUrl)
            .numOfLikes(0L)
            .build();
    }

    public CollectionResPagingDTO toCollectionResPagingDTO(List<CollectionResDTO> collectionResList, Boolean hasNext){
        return CollectionResPagingDTO.builder().collectionResList(collectionResList).hasNext(hasNext).build();
    }

    public CollectionsEntity toCollectionEntity(CollectionDTO collectionDTO) {
        return CollectionsEntity.builder()
                .title(collectionDTO.getTitle())
                .description(collectionDTO.getDescription())
                .color(collectionDTO.getColor())
                .favorite(collectionDTO.getFavorite())
                .imgURL(collectionDTO.getImgURL())
                .numOfLikes(collectionDTO.getNumOfLikes())
                .access(collectionDTO.getAccess()).build();
    }
}
