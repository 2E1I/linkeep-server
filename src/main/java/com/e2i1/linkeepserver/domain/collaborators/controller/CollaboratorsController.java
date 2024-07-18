package com.e2i1.linkeepserver.domain.collaborators.controller;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.collaborators.business.CollaboratorsBusiness;
import com.e2i1.linkeepserver.domain.collaborators.dto.CollaboratorEditReqDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/collaborators")
public class CollaboratorsController {
    private final CollaboratorsBusiness collaboratorsBusiness;

    @PutMapping()
    public ResponseEntity<String> EditCollaborators(@UserSession UsersEntity user,@RequestBody
        CollaboratorEditReqDTO collaboratorEditReqDTO){
        List<Long> insertUsers = collaboratorEditReqDTO.getInsertUsers();
        List<Long> deleteUsers = collaboratorEditReqDTO.getDeleteUsers();
        Long collectionId = collaboratorEditReqDTO.getCollectionId();
        collaboratorsBusiness.editCollaborators(user.getId(),collectionId,insertUsers,deleteUsers);
        return ResponseEntity.ok("success");
    }


}
