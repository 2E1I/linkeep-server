package com.e2i1.linkeepserver.domain.friends.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.friends.repository.FriendsRepository;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendsService {
    private final FriendsRepository friendsRepository;

  public List<FriendsEntity> getFollowers(UsersEntity user) {
    return friendsRepository.findByFollowedUserAndIsFollowing(user,true);
  }

  public List<FriendsEntity> getFollowings(UsersEntity user) {
    return friendsRepository.findByFollowingUserAndIsFollowing(user,true);
  }
  public FriendsEntity insertFriend(FriendsEntity friend) {
    return friendsRepository.save(friend);
  }

  public FriendsEntity findByFollowedUserAndFollowingUser(UsersEntity followee, UsersEntity follower) {
    return friendsRepository.findByFollowedUserAndFollowingUser(followee,follower).orElseThrow(() -> new ApiException(ErrorCode.FRINEDS_NOT_FOUND));
  }


}
