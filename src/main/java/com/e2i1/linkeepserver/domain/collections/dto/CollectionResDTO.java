package com.e2i1.linkeepserver.domain.collections.dto;

import com.e2i1.linkeepserver.domain.collaborators.dto.CollaboratorResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.Access;
import java.time.LocalDateTime;
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
    private int color;
    private boolean isLike;
    private Long numOfLikes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> tagList;
    private List<CollaboratorResDTO> collaboratorList;
}
