package com.e2i1.linkeepserver.domain.collaborators.repository;

import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollaboratorsRepository extends JpaRepository<CollaboratorsEntity, Long> {

    Optional<CollaboratorsEntity> findByUserIdAndCollectionId(Long userId, Long collectionId);

    @Query("select collection from CollaboratorsEntity where user =:user")
    Optional<List<CollectionsEntity>> findCollectionByUser(@Param("user") UsersEntity user);
}
