package com.e2i1.linkeepserver.domain.likeothers.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.likeothers.converter.LikeOthersConverter;
import com.e2i1.linkeepserver.domain.likeothers.service.LikeOthersService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class LikeOthersBusiness {
    private final LikeOthersService likeOthersService;
    private final LikeOthersConverter likeOthersConverter;

}
