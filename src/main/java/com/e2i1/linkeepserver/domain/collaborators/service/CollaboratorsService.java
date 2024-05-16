package com.e2i1.linkeepserver.domain.collaborators.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.collaborators.repository.CollaboratorsRepository;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollaboratorsService {
    private final CollaboratorsRepository collaboratorsRepository;

    public void findByUserIdAndCollectionIdWithThrow(Long userId, Long collectionId) {
        collaboratorsRepository.findByUserIdAndCollectionId(userId, collectionId)
            .orElseThrow(() -> new ApiException(ErrorCode.ACCESS_DENIED));
    }
    public List<CollectionsEntity> findCollectionByUser(UsersEntity user){

      return collaboratorsRepository.findCollectionByUser(user)
            .orElseThrow(() -> new ApiException(ErrorCode.COLLABORATOR_NOT_FOUND));
    }

    public void insert(CollaboratorsEntity collaborator) {
        collaboratorsRepository.save(collaborator);
    }

    public List<CollaboratorsEntity> findByCollection(CollectionsEntity collection) {
        return collaboratorsRepository.findByCollection(collection).orElseThrow(()->new ApiException(ErrorCode.COLLABORATOR_NOT_FOUND));
    }

    public long countCollection(UsersEntity user) {
        return collaboratorsRepository.countCollaboratorsEntitiesByUser(user);
    }
}
