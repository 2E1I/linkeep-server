package com.e2i1.linkeepserver.domain.collections.dto;

import com.e2i1.linkeepserver.domain.collaborators.dto.CollaboratorResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.Access;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "모음집 조회 응답 DTO",requiredProperties = {"collectionId", "title", "imgUrl","description","tags","access","color","isLike","numOfLikes","createdAt","updatedAt","tagList","collaboratorList"})
public class CollectionResDTO {
    @Schema(description = "모음집 ID", type = "long", example = "1")
    private Long collectionId;

    @Schema(description = "모음집 이름", type = "string", example = "웃음모음집")
    private String title;

    @Schema(description = "모음집 설명", type = "string", example = "제 모음집인디요.")
    private String description;

    @Schema(description = "대표 이미지 URL", type = "string", example = "http://example.com/image.jpg")
    private String imgUrl;

    @Schema(description = "모음집 공개 여부", type = "string", example = "PUBLIC")
    private String access;

    @Schema(description = "색상 코드", type = "integer", example = "1")
    private int color;

    @Schema(description = "좋아요 여부", type = "boolean", example = "true")
    private boolean isLike;

    @Schema(description = "좋아요 수", type = "long", example = "123")
    private Long numOfLikes;

    @Schema(description = "생성 날짜와 시간", type = "string", format = "date-time", example = "2023-05-17T12:34:56")
    private LocalDateTime createdAt;

    @Schema(description = "업데이트 날짜와 시간", type = "string", format = "date-time", example = "2023-05-18T12:34:56")
    private LocalDateTime updatedAt;

    @Schema(description = "태그 목록", type = "array", example = "[\"tag1\", \"tag2\"]")
    private List<String> tagList;

    @Schema(description = "공동 작업자 목록", type = "array", implementation = CollaboratorResDTO.class)
    private List<CollaboratorResDTO> collaboratorList;
}
