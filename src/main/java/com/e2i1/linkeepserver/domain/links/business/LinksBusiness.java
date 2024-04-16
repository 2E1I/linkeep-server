package com.e2i1.linkeepserver.domain.links.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.service.LinksService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class LinksBusiness {

    private final LinksService linksService;
    private final LinksConverter linksConverter;
    private final CollectionsService collectionsService;


    /**
     * link 저장하기
     */
    public LinksEntity save(LinkReqDTO req) {
        CollectionsEntity collection = collectionsService.findById(req.getCollectionId());
        LinksEntity linkEntity = linksConverter.toEntity(req, collection);
        return linksService.save(linkEntity);
    }
}
