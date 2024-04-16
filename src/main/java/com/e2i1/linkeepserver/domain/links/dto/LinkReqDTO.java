package com.e2i1.linkeepserver.domain.links.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkReqDTO {
    private String title;

    @NotBlank(message = "URL은 필수 입력입니다.")
    private String url;
    private String description;

    @NotBlank(message = "저장할 모음집을 선택하세요.")
    private Long collectionId;
}
