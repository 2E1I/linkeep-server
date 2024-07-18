package com.e2i1.linkeepserver.domain.collections.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CollectionsRepository extends JpaRepository<CollectionsEntity,Long> {
    @Lock(LockModeType.OPTIMISTIC)
    Optional<CollectionsEntity> findCollectionsEntityById(Long collectionId);
}
