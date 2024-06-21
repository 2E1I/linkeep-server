package com.e2i1.linkeepserver.domain.links.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionLinkDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkResDTO;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.users.dto.LinkHomeResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class LinksConverter {

    public LinksEntity toEntity(LinkReqDTO req, CollectionsEntity collection, UsersEntity user) {

        return LinksEntity.builder()
                .title(req.getTitle())
                .url(req.getUrl())
                .description(req.getDescription())
                .collection(collection)
                .user(user)
                .numOfViews(0L)
                .build();
    }

    public CollectionLinkDTO toCollectionLinkDTO(LinksEntity link) {
        return CollectionLinkDTO.builder()
                .linkId(link.getId())
                .title(link.getTitle())
                .description(link.getDescription())
                .numOfViews(link.getNumOfViews())
                .url(link.getUrl())
                .writer(link.getUser().getId())
                .updatedAt(link.getUpdateAt())
                .build();


    }

    public LinkResDTO toResponse(LinksEntity link) {
        return LinkResDTO.builder()
                .title(link.getTitle())
                .url(link.getUrl())
                .description(link.getDescription())
                .numOfViews(link.getNumOfViews())
                .writer(link.getUser().getId())
                .updatedAt(link.getUpdateAt())
                .build();
    }

    public SearchLinkDTO toSearchResponse(LinksEntity link) {
        return SearchLinkDTO.builder()
                .title(link.getTitle())
                .url(link.getUrl())
                .description(link.getDescription())
                .numOfViews(link.getNumOfViews())
                .linkId(link.getId())
                .writer(link.getUser().getId())
                .build();
    }

    public LinkHomeResDTO toLinkHomeResponse(LinksEntity link) {
        return LinkHomeResDTO.builder()
                .id(link.getId())
                .title(link.getTitle())
                .url(link.getUrl())
                .description(link.getDescription())
                .numOfViews(link.getNumOfViews())
                .updatedAt(link.getUpdateAt())
                .build();
    }

    public SearchLinkDTO docToSearchLinkDTO(LinkDocument document) {
        return SearchLinkDTO.builder()
                .title(document.getTitle())
                .url(document.getUrl())
                .description(document.getDescription())
                .numOfViews(document.getNumOfViews())
                .linkId(document.getId())
                .writer(Long.parseLong(document.getUserId()))
                .build();
    }

    public LinkDocument toDocument(LinksEntity link) {
        return LinkDocument.builder()
                .id(link.getId())
                .collectionId(String.valueOf(link.getCollection().getId()))
                .userId(String.valueOf(link.getUser().getId()))
                .title(link.getTitle())
                .url(link.getUrl())
                .description(link.getDescription())
                .numOfViews(link.getNumOfViews())
                .build();
    }

}
