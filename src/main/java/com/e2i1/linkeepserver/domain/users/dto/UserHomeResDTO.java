package com.e2i1.linkeepserver.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHomeResDTO {
    private String nickname;
    private String imgUrl;
    private List<LinkHomeResDTO> linkList;
    private Boolean hasNext; // 커서 기반 페이징할 때, 끝인지 아닌지 알려주는 변수
}
