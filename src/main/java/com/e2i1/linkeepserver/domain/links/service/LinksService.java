package com.e2i1.linkeepserver.domain.links.service;

import com.e2i1.linkeepserver.domain.links.repository.LinksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinksService {
    private final LinksRepository linksRepository;
}
