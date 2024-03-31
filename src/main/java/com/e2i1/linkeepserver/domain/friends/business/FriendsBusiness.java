package com.e2i1.linkeepserver.domain.friends.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.friends.converter.FriendsConverter;
import com.e2i1.linkeepserver.domain.friends.service.FriendsService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class FriendsBusiness {
    private final FriendsService friendsService;
    private final FriendsConverter friendsConverter;
}
