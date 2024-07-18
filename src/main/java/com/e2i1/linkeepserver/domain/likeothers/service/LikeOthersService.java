package com.e2i1.linkeepserver.domain.likeothers.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.likeothers.repository.LikeOthersRepository;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeOthersService {
    private final LikeOthersRepository likeOthersRepository;

  public boolean getIsLike(CollectionsEntity collection, UsersEntity user) {
    return likeOthersRepository.findByCollectionAndUser(collection, user).isPresent();
  }

  public void updateLike(LikeOthersEntity likeOther) {
    likeOthersRepository.save(likeOther);
  }

  public List<Long> findCollectionIdByUser(Long lastId, UsersEntity user, int size) {

    List<Long> collectionIdList = likeOthersRepository.findCollectionByUser(lastId,user.getId(),size);

    if(collectionIdList ==null)
    {
      throw new ApiException(ErrorCode.COLLABORATOR_NOT_FOUND);
    }
    return collectionIdList;

  }

  public void deleteLike(LikeOthersEntity likeOther) {
    likeOthersRepository.delete(likeOther);
  }

  public LikeOthersEntity findByCollectionAndUser(CollectionsEntity collection, UsersEntity user) {
    return likeOthersRepository.findByCollectionAndUser(collection,user).orElseThrow(() -> new ApiException(ErrorCode.LIKE_NOT_FOUND));
  }
}
