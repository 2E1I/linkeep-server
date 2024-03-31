package com.e2i1.linkeepserver.domain.collections.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchCollectionResDTO {
    private String title;
    private String imgUrl;
    private List<String> color;
    private int numOfViews;
    private List<String> tagNames;
}
