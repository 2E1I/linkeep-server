package com.e2i1.linkeepserver.domain.collections.dto;

import com.e2i1.linkeepserver.domain.collections.entity.Access;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String title;
    private String imgUrl;
    private String description;
    private List<String> tags;

    @NotNull
    private Access access;

    @NotNull
    private List<String> color;
    private List<Long> collaborators;
}
