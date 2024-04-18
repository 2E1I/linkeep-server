package com.e2i1.linkeepserver.domain.collaborators.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collaborators.repository.CollaboratorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollaboratorsService {
    private final CollaboratorsRepository collaboratorsRepository;

    public void findByUserIdAndCollectionIdWithThrow(Long userId, Long collectionId) {
        collaboratorsRepository.findByUserIdAndCollectionId(userId, collectionId)
            .orElseThrow(() -> new ApiException(ErrorCode.ACCESS_DENIED));
    }

}
