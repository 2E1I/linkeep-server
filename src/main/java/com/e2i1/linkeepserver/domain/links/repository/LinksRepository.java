package com.e2i1.linkeepserver.domain.links.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinksRepository extends JpaRepository<LinksEntity,Long> {

    public List<LinksEntity> findLinksEntitiesByCollection(CollectionsEntity collectionsEntity);

}
