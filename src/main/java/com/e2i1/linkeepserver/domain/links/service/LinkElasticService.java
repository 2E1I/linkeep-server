package com.e2i1.linkeepserver.domain.links.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.repository.LinkElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkElasticService {

    private final LinkElasticsearchRepository linkElasticsearchRepository;
    private final LinksConverter linkConverter;

    public Iterable<LinkDocument> getLinks() {
        return linkElasticsearchRepository.findAll();
    }

    public LinkDocument insertLink(LinkDocument document) {
        return linkElasticsearchRepository.save(document);
    }

    @Transactional
    public void edit(LinkDocument document) {
        LinkDocument link = linkElasticsearchRepository.findById(document.getId()).orElse(null);
        if (link == null) {
            throw new ApiException(ErrorCode.LINK_NOT_FOUND_IN_ES);
        }

        if (document.getTitle() != null && !document.getTitle().isEmpty()) {
            link.setTitle(document.getTitle());
        }
        if (document.getDescription() != null && !document.getDescription().isEmpty()) {
            link.setDescription(document.getDescription());
        }
        if (document.getUrl() != null && !document.getUrl().isEmpty()) {
            link.setUrl(document.getUrl());
        }

        linkElasticsearchRepository.save(link);
    }

    public List<LinkDocument> search(String keyword) {
        return linkElasticsearchRepository.searchByTitleOrDescription(keyword);
    }

    @Transactional
    public LinkDocument updateTitle(LinkDocument document, Long id) {
        LinkDocument link = linkElasticsearchRepository.findById(document.getId()).orElse(null);
        if (link == null) {
            throw new ApiException(ErrorCode.LINK_NOT_FOUND_IN_ES);
        }
        link.setTitle(document.getTitle());
        return link;
    }

    public void deleteDocument(Long id) {
        linkElasticsearchRepository.deleteById(id);
    }

    public Optional<LinkDocument> getLink(Long id) {
        return linkElasticsearchRepository.findById(id);
    }

//    public Map<String, Object> searchTitle(String title) {
//        SearchHits<LinkDocument> searchHits = linkElasticsearchRepository.findByTitle(title);
//
//        Map<String, Object> result = new HashMap<>();
//
//        result.put("count", searchHits.getTotalHits());
//
//        List<SearchLinkDTO> searchLinkList = new ArrayList<>();
//        for (SearchHit<LinkDocument> hit : searchHits) {
//            // hit -> SearchLinkDTO로 변환
//            LinkDocument doc = hit.getContent();
//            SearchLinkDTO dto = linkConverter.docToSearchLinkDTO(doc);
//            searchLinkList.add(dto);
//        }
//
//        result.put("data", searchLinkList);
//
//        return result;
//
//    }

}

