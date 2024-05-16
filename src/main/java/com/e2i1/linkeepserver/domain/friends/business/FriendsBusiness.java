package com.e2i1.linkeepserver.domain.friends.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import com.e2i1.linkeepserver.domain.friends.converter.FriendsConverter;
import com.e2i1.linkeepserver.domain.friends.dto.FriendsResDTO;
import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.friends.service.FriendsService;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class FriendsBusiness {
    private final FriendsService friendsService;
    private final UsersService usersService;
    private final CollaboratorsService collaboratorsService;
    private final FriendsConverter friendsConverter;


    public List<FriendsResDTO> getFollowers(UsersEntity user) {
        List<FriendsEntity> FriendsList = new ArrayList<>(user.getFollowingList());
        return FriendsList.stream().map(friend -> {
            long cnt = collaboratorsService.findCollectionByUser(friend.getFollowedUser()).size();
            return friendsConverter.toFriendsResDTO(friend.getFollowedUser(), cnt);
        }).toList();

    }

    public List<FriendsResDTO> getFollowings(UsersEntity user) {
        List<FriendsEntity> FriendsList = new ArrayList<>(user.getFollowerList());
        return FriendsList.stream().map(friend -> {
            long cnt = collaboratorsService.findCollectionByUser(friend.getFollowingUser()).size();
            return friendsConverter.toFriendsResDTO(friend.getFollowingUser(), cnt);
        }).toList();

    }

    public void insertFollow(String nickName, UsersEntity followingUser) {
        UsersEntity followedUser = usersService.findByNickName(nickName);
        FriendsEntity friend = friendsConverter.toFriendsEntity(followedUser,followingUser);
        friendsService.insertFriend(friend);
    }
}
