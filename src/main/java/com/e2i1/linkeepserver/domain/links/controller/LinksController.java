package com.e2i1.linkeepserver.domain.links.controller;

import com.e2i1.linkeepserver.domain.links.business.LinksBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LinksController {
    private final LinksBusiness linksBusiness;
}
