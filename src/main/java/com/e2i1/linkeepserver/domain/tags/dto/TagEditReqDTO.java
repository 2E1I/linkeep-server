package com.e2i1.linkeepserver.domain.tags.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagEditReqDTO {
    Long collectionId;
    List<String> deleteTags;
    List<String> insertTags;
}
