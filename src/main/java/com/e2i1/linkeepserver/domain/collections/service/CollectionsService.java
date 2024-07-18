package com.e2i1.linkeepserver.domain.collections.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collaborators.repository.CollaboratorsRepository;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.repository.CollectionsRepository;
import com.e2i1.linkeepserver.domain.links.dto.CollectionEditReqDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionsService {
    private final CollaboratorsRepository collaboratorsRepository;
    private final CollectionsRepository collectionsRepository;

    public CollectionsEntity findByIdWithThrow(Long collectionId) {
        return collectionsRepository.findCollectionsEntityById(collectionId)
                .orElseThrow(() -> {
                    log.error("Collection Not Found!! collectionId = {}", collectionId);
                    return new ApiException(ErrorCode.COLLECTION_NOT_FOUND);
                });
    }

  public void insert(CollectionsEntity collection) {
      collectionsRepository.save(collection);
  }

  public void deleteCollection(Long collectionId) {
      collectionsRepository.deleteById(collectionId);

  }

  public void editCollection(String imgUrl, CollectionEditReqDTO editReq, CollectionsEntity collection) {
      collection.update(imgUrl,editReq.getTitle(),editReq.getColor(),editReq.getAccess(),editReq.getDescription());

  }

//  public List<CollectionsEntity> searchCollection(String search, Long likes, Long lastId, int size,  Pageable pageable) {
//      return collectionsRepository.searchCollection(search,likes, lastId,size, pageable);
//  }

  public List<CollectionsEntity> searchCollection(String search, Long lastId, int size) {
    return collectionsRepository.searchCollection(search, lastId,size);
  }

    public List<CollectionsEntity> findCollectionByIds(List<Long> collectionIdList) {
        return collectionsRepository.findByCollectionIds(collectionIdList);
    }
}
