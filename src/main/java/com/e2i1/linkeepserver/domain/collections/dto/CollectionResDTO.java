package com.e2i1.linkeepserver.domain.collections.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResDTO {
    private Long collectionId;
    private String title;
    private String description;
    private String imgUrl;
    private String access;
    private List<String> color;
    private boolean favorite;
    private Long numOfLikes;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
