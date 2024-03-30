package com.e2i1.linkeepserver.domain.collections.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class CollectionsBusiness {
    private final CollectionsService collectionsService;
}
