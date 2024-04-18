package com.e2i1.linkeepserver.domain.collaborators.repository;

import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorsRepository extends JpaRepository<CollaboratorsEntity, Long> {

    Optional<CollaboratorsEntity> findByUserIdAndCollectionId(Long userId, Long collectionId);
}
