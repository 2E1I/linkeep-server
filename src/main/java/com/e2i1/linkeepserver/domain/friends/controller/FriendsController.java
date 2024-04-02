package com.e2i1.linkeepserver.domain.friends.controller;

import com.e2i1.linkeepserver.domain.friends.business.FriendsBusiness;
import com.e2i1.linkeepserver.domain.friends.dto.FriendsResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendsController {
    private final FriendsBusiness friendsBusiness;

    @GetMapping()
    public ResponseEntity<FriendsResDTO> getFriends(){
        return ResponseEntity.ok(null);
    }

    @PostMapping()
    public ResponseEntity<String> insertFriend(@RequestBody String nickName){
        return ResponseEntity.ok("success");
    }

    @PostMapping("/follow")
    public ResponseEntity<HashMap<String,String>> isFollow(@RequestBody Long userId){
        HashMap<String,String> result = new HashMap<>();
        result.put("isFollowing","Y");
        return ResponseEntity.ok(result);
    }


}
