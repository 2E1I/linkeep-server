package com.e2i1.linkeepserver.domain.tags.controller;

import com.e2i1.linkeepserver.domain.tags.business.TagsBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TagsController {
    private final TagsBusiness tagsBusiness;
}
