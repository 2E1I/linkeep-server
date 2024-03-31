package com.e2i1.linkeepserver.domain.likeothers.controller;

import com.e2i1.linkeepserver.domain.likeothers.business.LikeOthersBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeOthersController {
    private final LikeOthersBusiness likeOthersBusiness;
}
