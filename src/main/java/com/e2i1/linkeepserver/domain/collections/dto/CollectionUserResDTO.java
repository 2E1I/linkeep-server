package com.e2i1.linkeepserver.domain.collections.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionUserResDTO {
    private String title;
    private String imgUrl;
    private String description;
    List<String> tagList;
    List<CollectionLinkDTO> linkList;
}