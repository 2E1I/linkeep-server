package com.e2i1.linkeepserver.domain.collections.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCollectionResDTO {
    private String title;
    private String imgUrl;
    private int color;
    private int numOfViews;
    private List<String> tagNames;
}
