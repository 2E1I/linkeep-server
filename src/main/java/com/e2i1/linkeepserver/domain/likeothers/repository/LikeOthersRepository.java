package com.e2i1.linkeepserver.domain.likeothers.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface LikeOthersRepository extends JpaRepository<LikeOthersEntity,Long> {


  Optional<LikeOthersEntity> findByCollectionAndUser(CollectionsEntity collection, UsersEntity user);

  Optional<LikeOthersEntity> findByUser(UsersEntity user);

  @Query("select l.collection from LikeOthersEntity l where l.user =: user")
  Optional<List<CollectionsEntity>> findCollectionByUser(@Param("user") UsersEntity user);
}
