package com.e2i1.linkeepserver.domain.friends.service;

import com.e2i1.linkeepserver.domain.friends.repository.FriendsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendsService {
    private FriendsRepository friendsRepository;
}
