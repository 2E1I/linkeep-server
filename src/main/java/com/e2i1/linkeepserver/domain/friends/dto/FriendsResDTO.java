package com.e2i1.linkeepserver.domain.friends.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendsResDTO {
    private String nickName;
    private Long userId;
    private String imgUrl;
    private String description;
    private Long numOfCollections;

}
