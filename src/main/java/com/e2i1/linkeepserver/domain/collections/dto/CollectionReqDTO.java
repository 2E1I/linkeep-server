package com.e2i1.linkeepserver.domain.collections.dto;

import com.e2i1.linkeepserver.domain.collections.entity.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionReqDTO {
    private String title;
    private String imgUrl;
    private String description;
    private List<String> tags;
    private Access access;
    private List<Integer> color;
    private List<Long> collaborators;
}
