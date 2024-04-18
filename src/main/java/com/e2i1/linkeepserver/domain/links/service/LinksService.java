package com.e2i1.linkeepserver.domain.links.service;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.repository.LinksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinksService {
    private final LinksRepository linksRepository;

    public LinksEntity save(LinksEntity link) {
        return linksRepository.save(link);
    }

    public List<LinksEntity> findByCollections(CollectionsEntity collections){
        return linksRepository.findLinksEntitiesByCollection(collections);
    }


}
