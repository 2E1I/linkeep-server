package com.e2i1.linkeepserver.domain.links.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.service.LinksService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class LinksBusiness {
    private final LinksService linksService;
    private final LinksConverter linksConverter;
}
