package com.e2i1.linkeepserver.domain.links.service;

import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.repository.LinkElasticsearchRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LinkElasticService {

    private final LinkElasticsearchRepository linkRepository;
    private final LinksConverter linkConverter;

    public Iterable<LinkDocument> getLinks() {
        return linkRepository.findAll();
    }
    public LinkDocument insertLink(LinkDocument document) {
        return linkRepository.save(document);
    }

    @Transactional
    public LinkDocument updateTitle(LinkDocument document, String id) {
        LinkDocument link = linkRepository.findById(id).get();
        link.setTitle(document.getTitle());

        return link;
    }

    public void deleteDocument(String id) {
        linkRepository.deleteById(id);
    }

    public Optional<LinkDocument> getLink(String id) {
        return linkRepository.findById(id);
    }

    public Map<String, Object> searchTitle(String title) {
        SearchHits<LinkDocument> searchHits = linkRepository.findByTitle(title);

        Map<String, Object> result = new HashMap<>();

        result.put("count", searchHits.getTotalHits());

        List<SearchLinkDTO> searchLinkList = new ArrayList<>();
        for (SearchHit<LinkDocument> hit : searchHits) {
            // hit -> SearchLinkDTO로 변환
            LinkDocument doc = hit.getContent();
            SearchLinkDTO dto = linkConverter.docToSearchLinkDTO(doc);
            searchLinkList.add(dto);
        }

        result.put("data", searchLinkList);

        return result;

    }


}
