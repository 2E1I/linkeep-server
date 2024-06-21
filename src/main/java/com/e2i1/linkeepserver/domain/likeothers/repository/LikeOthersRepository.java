package com.e2i1.linkeepserver.domain.likeothers.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeOthersRepository extends JpaRepository<LikeOthersEntity,Long> {


  Optional<LikeOthersEntity> findByCollectionAndUser(CollectionsEntity collection, UsersEntity user);

  Optional<LikeOthersEntity> findByUser(UsersEntity user);

  @Query("select l.collection from LikeOthersEntity l where l.user =:user and l.collection.id < :lastId and l.collection.id not in (select c.collection.id from CollaboratorsEntity c where c.user=:user)order by l.collection.id desc")
  Optional<List<CollectionsEntity>> findCollectionByUser(@Param("lastId") Long lastId,@Param("user") UsersEntity user,
      Pageable pageable);
}
