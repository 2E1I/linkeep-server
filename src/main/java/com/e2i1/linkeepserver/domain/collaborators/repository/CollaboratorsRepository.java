package com.e2i1.linkeepserver.domain.collaborators.repository;

import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.collaborators.entity.Role;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollaboratorsRepository extends JpaRepository<CollaboratorsEntity, Long> {

    Optional<CollaboratorsEntity> findByUserIdAndCollectionId(Long userId, Long collectionId);

    @Query("select collection from CollaboratorsEntity where user =:user")
    Optional<List<CollectionsEntity>> findCollectionByUser(@Param("user") UsersEntity user);

    @Query("select c.user from CollaboratorsEntity c where c.collection=:collection")
    Optional<List<UsersEntity>> findCollaboratorsByCollection(@Param("collection") CollectionsEntity collection);

    Optional<List<CollaboratorsEntity>> findByCollection(CollectionsEntity collection);

    @Query("select count(*) from CollaboratorsEntity where user=:user")
    long countCollectionByUser(@Param("user") UsersEntity user);

    long countCollaboratorsEntitiesByUser(UsersEntity user);

    CollaboratorsEntity findCollaboratorsEntityByCollectionIdAndRole(Long collection_id, Role role);

    @Query("select collection from CollaboratorsEntity where user =:user and collection.id <:lastId order by collection.id desc")
    Optional<List<CollectionsEntity>>  findByUserAndCollectionIdLessThanOrderByCollectionIdDesc(UsersEntity user, Long lastId, Pageable pageable);
}
