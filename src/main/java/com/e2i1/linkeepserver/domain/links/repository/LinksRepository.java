package com.e2i1.linkeepserver.domain.links.repository;

import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinksRepository extends JpaRepository<LinksEntity,Long> {

}
