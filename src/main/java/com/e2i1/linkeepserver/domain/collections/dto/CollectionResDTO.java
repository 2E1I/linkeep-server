package com.e2i1.linkeepserver.domain.collections.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.BindParam;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CollectionResDTO {
    private Long collectionId;
    private String title;
    private String description;
    private String imgUrl;
    private String access;
    private List<String> color;
    private boolean favorite;
    private Long numOfLikes;
    private LocalDate creatAt;
    private LocalDate updateAt;
}
