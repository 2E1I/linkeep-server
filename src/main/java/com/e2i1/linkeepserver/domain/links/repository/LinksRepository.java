package com.e2i1.linkeepserver.domain.links.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface LinksRepository extends JpaRepository<LinksEntity, Long> {

    Optional<LinksEntity> findFirstByIdOrderByIdDesc(Long linkId);

    @Query("select l from LinksEntity l where "
        + "REPLACE(lower(l.title), ' ', '' ) LIKE CONCAT('%', lower(:keyword), '%') "
        + "OR REPLACE(lower(l.description), ' ', '' ) LIKE CONCAT('%', lower(:keyword), '%') "
        + "order by l.numOfViews desc ")
    List<LinksEntity> findByTitleOrDescriptionContainingKeyword(@Param("keyword") String keyword);

    List<LinksEntity> findLinksEntitiesByCollection(CollectionsEntity collectionsEntity);

    List<LinksEntity> findByUserIdOrderByUpdateAtDesc(Long userId);
}
