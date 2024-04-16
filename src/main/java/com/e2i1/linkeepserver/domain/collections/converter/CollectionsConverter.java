package com.e2i1.linkeepserver.domain.collections.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionUserResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class CollectionsConverter {

    public CollectionUserResDTO toDTO(CollectionsEntity collection){
        CollectionUserResDTO userResDTO = CollectionUserResDTO.builder()
                .title(collection.getTitle())
                .imgUrl(collection.getImgURL())
                .description(collection.getDescription())
                .linkList(null)
                .tagList(null)
                .build();
        return userResDTO;
    }

}
