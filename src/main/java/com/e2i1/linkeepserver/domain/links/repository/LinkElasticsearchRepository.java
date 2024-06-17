package com.e2i1.linkeepserver.domain.links.repository;

import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LinkElasticsearchRepository extends ElasticsearchRepository<LinkDocument, String> {
    SearchHits<LinkDocument> findByTitle(String title);
}
