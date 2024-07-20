package com.e2i1.linkeepserver.domain.collections.dto;

import com.e2i1.linkeepserver.domain.collections.entity.Access;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionDTO {
    private String title;

    private String description;

    private String imgURL;

    @Enumerated(EnumType.STRING)
    private Access access;


    private int color;

    private Boolean favorite;

    private Long numOfLikes;

    @Version
    private Long version;
}
