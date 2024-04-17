package com.e2i1.linkeepserver.domain.links.repository;

import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinksRepository extends JpaRepository<LinksEntity,Long> {

    Optional<LinksEntity> findFirstByIdOrderByIdDesc(Long linkId);
}
