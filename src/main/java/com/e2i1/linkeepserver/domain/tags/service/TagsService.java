package com.e2i1.linkeepserver.domain.tags.service;

import com.e2i1.linkeepserver.domain.tags.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagsService {
    private final TagsRepository tagsRepository;
}
