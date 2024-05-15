package com.e2i1.linkeepserver.domain.friends.service;

import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.friends.repository.FriendsRepository;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendsService {
    private FriendsRepository friendsRepository;

  public List<FriendsEntity> getFollowers(UsersEntity user) {
    return friendsRepository.findByFollowedUser(user);
  }
}
