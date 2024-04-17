package com.e2i1.linkeepserver.domain.links.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkResDTO {
    private Long writer;
    private String url;
    private Long numOfViews;
    private String title;
    private String description;
    private LocalDateTime updatedAt;
}
