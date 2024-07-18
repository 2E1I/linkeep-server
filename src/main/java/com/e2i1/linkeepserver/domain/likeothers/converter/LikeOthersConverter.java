package com.e2i1.linkeepserver.domain.likeothers.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersId;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Converter
@RequiredArgsConstructor
public class LikeOthersConverter {

  public LikeOthersEntity toLikeOthersEntity(CollectionsEntity collection, UsersEntity user) {
    return LikeOthersEntity.builder()
        .id(new LikeOthersId(user.getId(), collection.getId()))
        .collection(collection)
        .user(user).createdAt(LocalDateTime.now())
        .build();
  }
}
