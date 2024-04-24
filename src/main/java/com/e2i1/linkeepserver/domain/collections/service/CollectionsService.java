package com.e2i1.linkeepserver.domain.collections.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collaborators.repository.CollaboratorsRepository;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.repository.CollectionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionsService {
    private final CollaboratorsRepository collaboratorsRepository;
    private final CollectionsRepository collectionsRepository;

    public CollectionsEntity findByIdWithThrow(Long collectionId) {
        return collectionsRepository.findById(collectionId)
                .orElseThrow(() -> {
                    log.error("Collection Not Found!! collectionId = {}", collectionId);
                    return new ApiException(ErrorCode.COLLECTION_NOT_FOUND);
                });
    }

  public void insert(CollectionsEntity collection) {
      collectionsRepository.save(collection);
  }
}
