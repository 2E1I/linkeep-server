package com.e2i1.linkeepserver.domain.links.repository;

import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LinkElasticsearchRepository extends ElasticsearchRepository<LinkDocument, Long> {
    @Query("{\"bool\": {\"should\": [" +
            "{\"match\": {\"title\": \"?0\"}}," +
            "{\"match\": {\"description\": \"?0\"}}" +
            "]}}")
    List<LinkDocument> searchByTitleOrDescription(String keyword);

}
