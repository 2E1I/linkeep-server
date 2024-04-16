package com.e2i1.linkeepserver.domain.collections.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collections.converter.CollectionsConverter;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionUserResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class CollectionsBusiness {
    private final CollectionsService collectionsService;
    private final CollectionsConverter collectionsConverter;

    public CollectionUserResDTO getCollection(Long id){
        CollectionsEntity collection = collectionsService.findById(id);
        CollectionUserResDTO result = collectionsConverter.toDTO(collection);
        return result;
    }
}
