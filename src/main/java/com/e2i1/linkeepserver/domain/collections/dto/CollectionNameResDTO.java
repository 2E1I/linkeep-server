package com.e2i1.linkeepserver.domain.collections.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionNameResDTO {
    private Long collectionId;
    private String title;

}
