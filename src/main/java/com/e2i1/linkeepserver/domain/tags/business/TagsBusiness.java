package com.e2i1.linkeepserver.domain.tags.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.tags.converter.TagsConverter;
import com.e2i1.linkeepserver.domain.tags.service.TagsService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class TagsBusiness {
    private final TagsService tagsService;
    private final TagsConverter tagsConverter;
}
