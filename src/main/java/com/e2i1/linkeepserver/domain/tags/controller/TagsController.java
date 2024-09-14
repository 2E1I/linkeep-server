package com.e2i1.linkeepserver.domain.tags.controller;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.tags.business.TagsBusiness;
import com.e2i1.linkeepserver.domain.tags.dto.TagEditReqDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagsController {
    private final TagsBusiness tagsBusiness;


    @PutMapping()
    public ResponseEntity<String> EditTags(@UserSession UsersEntity user, @RequestBody
    TagEditReqDTO tagEditReqDTO){
        List<String> insertTags = tagEditReqDTO.getInsertTags();
        List<String> deleteTags = tagEditReqDTO.getDeleteTags();
        Long collectionId = tagEditReqDTO.getCollectionId();
        tagsBusiness.editTags(user.getId(),collectionId,insertTags,deleteTags);
        return ResponseEntity.ok("success");
    }


}
