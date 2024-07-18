package com.e2i1.linkeepserver.domain.likeothers.repository;

import com.e2i1.linkeepserver.domain.collections.dto.CollectionDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface LikeOthersRepository extends JpaRepository<LikeOthersEntity,Long> {


  Optional<LikeOthersEntity> findByCollectionAndUser(CollectionsEntity collection, UsersEntity user);

  Optional<LikeOthersEntity> findByUser(UsersEntity user);

  @Query(value = "select l.collection_id from like_others l " +
          "          where l.user_id =:userId \n" +
          "          AND (:lastId IS NULL OR l.created_at < (SELECT i.created_at FROM like_others i WHERE (i.collection_id =:lastId and i.user_id =:userId) limit 1))\n" +
          "          order by l.created_at desc, l.collection_id desc\n" +
          "          LIMIT :size ;", nativeQuery = true)
  List<Long> findCollectionByUser(@Param("lastId") Long lastId, @Param("userId") Long userId,
                                           @Param("size") int size );
}
