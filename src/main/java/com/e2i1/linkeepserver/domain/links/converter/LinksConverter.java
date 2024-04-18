package com.e2i1.linkeepserver.domain.links.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionLinkDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class LinksConverter {

    public LinksEntity toEntity(LinkReqDTO req, CollectionsEntity collection) {

        return LinksEntity.builder()
                .title(req.getDescription())
                .url(req.getUrl())
                .description(req.getDescription())
                .collection(collection)
                .build();
    }
    public CollectionLinkDTO toCollectionLinkDTO(LinksEntity link){
        return CollectionLinkDTO.builder()
                .linkId(link.getId())
                .description(link.getDescription())
                .numOfViews(link.getNumOfViews())
                .url(link.getUrl())
                .writer(link.getUser().getId())
                .updatedAt(link.getUpdateAt())
                .build();


    }

//    public LinkResDTO toResponse(LinksEntity link) {
//
//    }
}
