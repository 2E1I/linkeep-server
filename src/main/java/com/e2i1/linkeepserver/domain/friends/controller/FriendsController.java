package com.e2i1.linkeepserver.domain.friends.controller;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.friends.business.FriendsBusiness;
import com.e2i1.linkeepserver.domain.friends.dto.FriendReqDTO;
import com.e2i1.linkeepserver.domain.friends.dto.FriendStatusReqDTO;
import com.e2i1.linkeepserver.domain.friends.dto.FriendStatusResDTO;
import com.e2i1.linkeepserver.domain.friends.dto.FriendsResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendsController {
    private final FriendsBusiness friendsBusiness;

    @GetMapping("/follower")
    public ResponseEntity<List<FriendsResDTO>> getFollowers(@UserSession UsersEntity user){
        return ResponseEntity.ok(friendsBusiness.getFollowers(user));
    }

    @GetMapping("/following")
    public ResponseEntity<List<FriendsResDTO>> getFollowings(@UserSession UsersEntity user){
        return ResponseEntity.ok(friendsBusiness.getFollowings(user));
    }

    @PostMapping()
    public ResponseEntity<String> insertFriend(@RequestBody FriendReqDTO friendReqDTO, @UserSession UsersEntity user){
        friendsBusiness.insertFollow(friendReqDTO.getNickname(), user);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/follow")
    public ResponseEntity<FriendStatusResDTO> isFollow(@RequestBody FriendStatusReqDTO reqDTO, @UserSession UsersEntity user){

        return ResponseEntity.ok(friendsBusiness.changeStatus(reqDTO.getUserId(),user));
    }


}
