package com.e2i1.linkeepserver.domain.collections.dto;

import com.e2i1.linkeepserver.domain.collections.entity.Access;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "모음집 추가 요청 DTO",requiredProperties = {"title", "imgUrl","description","tags","access","color","collaborators"})
public class CollectionReqDTO {
    @Schema(description = "모음집 이름", type = "string", example = "유유정의 모음집 1")
    @NotNull
    private String title;

    @Schema(description = "모음집 설명", type = "string", example = "이 모음집은 제 모음집입니다.")
    private String description;

    @Schema(description = "태그 목록", type = "array", example = "[\"tag1\", \"tag2\"]")
    private List<String> tags;

    @Schema(description = "모음집 공개 범위", type = "string", example = "PUBLIC")
    @NotNull
    private Access access;

    @Schema(description = "색상 코드", type = "integer", example = "1")
    @NotNull
    private int color;

    @Schema(description = "공동 작업자 ID 목록", type = "array", example = "[1, 2, 3]")
    private List<Long> collaborators;
}
