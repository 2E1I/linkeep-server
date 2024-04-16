package com.e2i1.linkeepserver.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String nickname;
    private String imgUrl;
    private String description;
}
