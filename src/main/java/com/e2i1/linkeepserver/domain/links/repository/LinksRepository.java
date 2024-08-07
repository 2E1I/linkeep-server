package com.e2i1.linkeepserver.domain.links.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface LinksRepository extends JpaRepository<LinksEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<LinksEntity> findFirstByIdOrderByIdDesc(Long linkId);

    @Query("SELECT l FROM LinksEntity l "
        + "WHERE (REPLACE(lower(l.title), ' ', '' ) LIKE CONCAT('%', lower(:keyword), '%') "
        + "OR REPLACE(lower(l.description), ' ', '' ) LIKE CONCAT('%', lower(:keyword), '%')) "
        + "AND (l.numOfViews < :view OR (l.numOfViews = :view AND l.id > :lastId))"
        + "ORDER BY l.numOfViews desc, l.id ASC ")
    List<LinksEntity> findByTitleOrDescriptionContainingKeyword(@Param("keyword") String keyword,
        @Param("view") Long view, @Param("lastId") Long lastId, Pageable pageable);

    List<LinksEntity> findLinksEntitiesByCollection(CollectionsEntity collectionsEntity);

    /**
     * JPQL에서는 LIMIT을 쓰지 못해서 nativeQuery로 만듦
     */
    @Query(value = "SELECT l.* FROM links l "
        + "WHERE l.user_id = :userId "
        + "AND (:lastId IS NULL OR l.created_at < (SELECT i.created_at FROM links i WHERE i.id = :lastId LIMIT 1)) "
        + "ORDER BY l.created_at DESC "
        + "LIMIT :size", nativeQuery = true)
    List<LinksEntity> findByUserIdAndIdLessThan(@Param("userId") Long userId, @Param("lastId") Long lastId, @Param("size") int size);

}
