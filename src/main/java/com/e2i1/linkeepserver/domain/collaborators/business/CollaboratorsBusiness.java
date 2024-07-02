package com.e2i1.linkeepserver.domain.collaborators.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collaborators.converter.CollaboratorsConverter;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.collaborators.entity.Role;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Business
@RequiredArgsConstructor
public class CollaboratorsBusiness {
    private final CollaboratorsService collaboratorsService;
    private final CollectionsService collectionsService;
    private final UsersService usersService;
    private final CollaboratorsConverter collaboratorsConverter;
    @Transactional
    public void editCollaborators(Long userId, Long collectionId, List<Long> insertUsers, List<Long> deleteUsers) {
      Long ownerId = collaboratorsService.findCollectionOwner(collectionId);
      if (Objects.equals(ownerId, userId)) {
        if (deleteUsers !=null)
          collaboratorsService.deleteAllById(deleteUsers,collectionId);
        if (insertUsers !=null){
          CollectionsEntity collection = collectionsService.findByIdWithThrow(collectionId);
          List<CollaboratorsEntity> collaborators = insertUsers.stream().map(insertUserId->{
            UsersEntity insertUser = usersService.findById(insertUserId);
            return collaboratorsConverter.toEntity(collection,insertUser, Role.COWORKER);
          }).toList();

          collaboratorsService.insertAll(collaborators);

        }


      } else {
        throw new ApiException(ErrorCode.COLLECTION_UNAUTHORIZED);
      }

    }
}
