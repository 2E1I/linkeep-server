package com.e2i1.linkeepserver.domain.likeothers.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface LikeOthersRepository extends JpaRepository<LikeOthersEntity,Long> {


  Optional<LikeOthersEntity> findByCollectionAndUser(CollectionsEntity collection, UsersEntity user);

  Optional<LikeOthersEntity> findByUser(UsersEntity user);

  @Query(value = "SELECT c.* FROM collections c " +
          "JOIN like_others l ON c.id = l.collection_id " +
          "WHERE l.user_id = :userId " +
          "AND (:lastId IS NULL OR l.created_at < (SELECT created_at FROM like_others WHERE id = :lastId)) " +
          "ORDER BY l.created_at DESC " +
          "LIMIT :size", nativeQuery = true)
  List<CollectionsEntity> findCollectionByUser(@Param("lastId") Long lastId,@Param("userId") Long userId,
      @Param("size") int size );
}
