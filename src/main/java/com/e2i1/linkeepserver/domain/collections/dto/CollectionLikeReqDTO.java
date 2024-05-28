package com.e2i1.linkeepserver.domain.collections.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "모음집 좋아요 업데이트 요청 DTO",requiredProperties = {"collectionId", "flag"})
public class CollectionLikeReqDTO {
  @Schema(description = "모음집 id", type = "long", example = "4")
  private long collectionId;
  @Schema(description = "좋아요 증가 여부",type = "boolean", example = "true")
  private boolean flag;
}
