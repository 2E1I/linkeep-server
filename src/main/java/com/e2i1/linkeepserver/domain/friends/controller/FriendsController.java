package com.e2i1.linkeepserver.domain.friends.controller;

import com.e2i1.linkeepserver.domain.friends.business.FriendsBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendsController {
    private final FriendsBusiness friendsBusiness;

}
