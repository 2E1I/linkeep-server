package com.e2i1.linkeepserver.domain.collections.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionsRepository extends JpaRepository<CollectionsEntity,Long> {
}
