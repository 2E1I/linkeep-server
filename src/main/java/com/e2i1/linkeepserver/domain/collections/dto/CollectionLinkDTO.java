package com.e2i1.linkeepserver.domain.collections.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionLinkDTO {
    private Long linkId;
    private Long writer;
    private String url;
    private Long numOfViews;
    private String title;
    private String description;
    private LocalDate updatedAt;
}
