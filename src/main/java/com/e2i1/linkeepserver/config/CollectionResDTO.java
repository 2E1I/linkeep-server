package com.e2i1.linkeepserver.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionResDTO {
    long collectionId;
    String title;
    String description;
}
