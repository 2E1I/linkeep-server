package com.e2i1.linkeepserver.domain.friends.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.friends.dto.FriendStatusResDTO;
import com.e2i1.linkeepserver.domain.friends.dto.FriendsResDTO;
import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.friends.entity.FriendsId;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class FriendsConverter {

  public  FriendsEntity toFriendsEntity(UsersEntity followedUser, UsersEntity followingUser) {
    return FriendsEntity.builder()
        .id(new FriendsId(followingUser.getId(),followedUser.getId()))
        .followedUser(followedUser)
        .followingUser(followingUser)
        .isFollowing(true).build();
  }

  public FriendsResDTO toFriendsResDTO(UsersEntity user, long cnt) {
    return FriendsResDTO.builder().nickName(user.getNickname()).userId(user.getId()).numOfCollections(cnt).build();
  }
  public FriendStatusResDTO toFriendStatusResDTO(boolean followStatus){
    return FriendStatusResDTO.builder().followStatus(followStatus).build();
  }
}
