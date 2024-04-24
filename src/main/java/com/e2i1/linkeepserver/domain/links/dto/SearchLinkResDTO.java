package com.e2i1.linkeepserver.domain.links.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchLinkResDTO {
    private String title;
    private String url;
    private String description;
    private Long numOfViews;
    private Long linkId;
    private Long writer;

}
