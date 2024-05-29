package com.e2i1.linkeepserver.domain.likeothers.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeOthersId implements Serializable {
  private Long userId;
  private Long collectionId;


}

