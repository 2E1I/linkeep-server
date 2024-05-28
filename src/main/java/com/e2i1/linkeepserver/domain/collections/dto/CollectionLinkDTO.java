package com.e2i1.linkeepserver.domain.collections.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "모음집 내 링크 DTO", requiredProperties = {"linkId", "writer", "url", "numOfViews", "title", "description"})
public class CollectionLinkDTO {

    @Schema(description = "링크 ID", type = "long", example = "1")
    private Long linkId;

    @Schema(description = "작성자 ID", type = "long", example = "2")
    private Long writer;

    @Schema(description = "링크 URL", type = "string", example = "http://example.com")
    private String url;

    @Schema(description = "조회수", type = "long", example = "100")
    private Long numOfViews;

    @Schema(description = "제목", type = "string", example = "슬플때 보면 좋은 영상")
    private String title;

    @Schema(description = "설명",type = "string", example = "3:30 웃음벨 영상")
    private String description;

    @Schema(description = "업데이트 날짜와 시간", type = "string", example = "2023-05-17T12:34:56")
    private LocalDateTime updatedAt;
}
