package com.e2i1.linkeepserver.domain.links.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class LinksConverter {

    public LinksEntity toEntity(LinkReqDTO req, CollectionsEntity collection, UsersEntity user) {

        return LinksEntity.builder()
                .title(req.getDescription())
                .url(req.getUrl())
                .description(req.getDescription())
                .collection(collection)
                .user(user)
                .build();
    }

//    public LinkResDTO toResponse(LinksEntity link) {
//
//    }
}
