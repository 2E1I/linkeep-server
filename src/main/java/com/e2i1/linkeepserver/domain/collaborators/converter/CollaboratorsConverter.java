package com.e2i1.linkeepserver.domain.collaborators.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collaborators.business.CollaboratorsBusiness;
import com.e2i1.linkeepserver.domain.collaborators.dto.CollaboratorResDTO;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsId;
import com.e2i1.linkeepserver.domain.collaborators.entity.Role;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class CollaboratorsConverter {

    private final CollaboratorsBusiness collaboratorsBusiness;

  public CollaboratorsEntity toEntity(CollectionsEntity collection, UsersEntity user, Role role) {
    return CollaboratorsEntity.builder()
        .id(new CollaboratorsId(collection.getId(),user.getId()))
        .role(role)
        .collection(collection)
        .user(user)
        .build();
  }

  public CollaboratorResDTO toCollaboratorResDTO(CollaboratorsEntity collaborator, UsersEntity user){
    return CollaboratorResDTO.builder()
        .userId(user.getId())
        .name(user.getNickname())
        .role(collaborator.getRole().getDescription())
        .build();
  }
}
