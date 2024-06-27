package com.e2i1.linkeepserver.domain.collections.dto;

import com.e2i1.linkeepserver.domain.collaborators.dto.CollaboratorResDTO;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
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
    private boolean isLike;
    private List<String> tagList;
    private List<CollectionLinkDTO> linkList;
    private List<CollaboratorResDTO> collaboratorResList;
}
