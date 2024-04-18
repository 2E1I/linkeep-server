package com.e2i1.linkeepserver.domain.collections.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionLinkDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionUserResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
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

}
