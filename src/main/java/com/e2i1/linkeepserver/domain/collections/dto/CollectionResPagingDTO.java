package com.e2i1.linkeepserver.domain.collections.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResPagingDTO {
  private List<CollectionResDTO> collectionResList;
  private Boolean hasNext;

}
