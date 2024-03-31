package com.e2i1.linkeepserver.domain.likeothers.service;

import com.e2i1.linkeepserver.domain.likeothers.repository.LikeOthersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeOthersService {
    private final LikeOthersRepository likeOthersRepository;
}
