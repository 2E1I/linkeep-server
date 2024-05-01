package com.e2i1.linkeepserver.domain.likeothers.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class LikeOthersConverter {

  public LikeOthersEntity toLikeOthersEntity(CollectionsEntity collection, UsersEntity user) {
    return LikeOthersEntity.builder().collection(collection).user(user).build();
  }
}
