package com.e2i1.linkeepserver.domain.friends.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.friends.dto.FriendsResDTO;
import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class FriendsConverter {

  public FriendsResDTO toFriendsResDTO(String nickname, long cnt) {
    return FriendsResDTO.builder().nickName(nickname).numOfCollections(cnt).build();
  }
}
