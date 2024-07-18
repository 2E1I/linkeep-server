package com.e2i1.linkeepserver.domain.collections.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CollectionsRepository extends JpaRepository<CollectionsEntity,Long> {

  @Query(value = "select * \n "
      + "from collections c \n "
      + "where (to_tsvector(c.description) @@ to_tsquery(:search) or to_tsvector(c.title) @@ to_tsquery(:search))\n"
      + "and (:lastId IS NULL OR ts_rank(setweight(to_tsvector(coalesce(c.title, '')), 'A') ||\n"
      + "            setweight(to_tsvector(coalesce(c.description, '')), 'B'),\n"
      + "            to_tsquery(:search))\n"
      + "        < (select ts_rank(setweight(to_tsvector(coalesce(c1.title, '')), 'A') ||\n"
      + "                          setweight(to_tsvector(coalesce(c1.description, '')), 'B'),\n"
      + "                          to_tsquery(:search)) from collections c1 where c1.id =:lastId)\n"
      + "    or (ts_rank(setweight(to_tsvector(coalesce(c.title, '')), 'A') ||\n"
      + "                setweight(to_tsvector(coalesce(c.description, '')), 'B'),\n"
      + "                to_tsquery(:search))\n"
      + "        = (select ts_rank(setweight(to_tsvector(coalesce(c2.title, '')), 'A') ||\n"
      + "                          setweight(to_tsvector(coalesce(c2.description, '')), 'B'),\n"
      + "                          to_tsquery(:search)) from collections c2 where c2.id =:lastId)\n"
      + "        and c.created_at < (SELECT c3.created_at FROM collections c3 WHERE c3.id = :lastId LIMIT 1)))\n"
      + "\n"
      + "order by ts_rank(setweight(to_tsvector(coalesce(c.title, '')), 'A') ||\n"
      + "                 setweight(to_tsvector(coalesce(c.description, '')), 'B'),\n"
      + "                 to_tsquery(:search)) desc, c.created_at desc\n"
      + "limit :size",
      nativeQuery = true)
  List<CollectionsEntity> searchCollection(@Param("search") String search, @Param("lastId") Long lastId, @Param("size") int size);

    @Lock(LockModeType.OPTIMISTIC)
    Optional<CollectionsEntity> findCollectionsEntityById(Long collectionId);
}
