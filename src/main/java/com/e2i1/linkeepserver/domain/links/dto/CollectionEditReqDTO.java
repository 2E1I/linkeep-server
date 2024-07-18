package com.e2i1.linkeepserver.domain.links.dto;

import com.e2i1.linkeepserver.domain.collections.entity.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionEditReqDTO {
    private Boolean isDeletedImg;
    private String title;
    private int color;
    private Access access;
    private String description;
}
