package com.e2i1.linkeepserver.domain.friends.repository;

import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface FriendsRepository extends JpaRepository<FriendsEntity,Long> {
  public List<FriendsEntity> findByFollowedUser(UsersEntity user);
  Optional <FriendsEntity> findByFollowedUserAndFollowingUser(UsersEntity followee, UsersEntity follower);

}
