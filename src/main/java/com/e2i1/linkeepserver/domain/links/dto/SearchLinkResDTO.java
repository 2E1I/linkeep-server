package com.e2i1.linkeepserver.domain.links.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchLinkResDTO {

    List<SearchLinkDTO> searchLinkList;
    Boolean hasNext;

}
