package com.e2i1.linkeepserver.domain.links.service;


import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.repository.LinksRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinksService {

    private final LinksRepository linksRepository;

    public LinksEntity save(LinksEntity link) {
        return linksRepository.save(link);
    }


    public List<LinksEntity> findByCollections(CollectionsEntity collections) {
        return linksRepository.findLinksEntitiesByCollection(collections);
    }

    public LinksEntity findOneByIdAndUserId(Long linkId) {
        return linksRepository.findFirstByIdOrderByIdDesc(linkId)
            .orElseThrow(() -> new ApiException(ErrorCode.LINK_NOT_FOUND));
    }


    public List<LinksEntity> searchLinks(String keyword) {
        return linksRepository.findByTitleOrDescriptionContainingKeyword(keyword);
    }
}
