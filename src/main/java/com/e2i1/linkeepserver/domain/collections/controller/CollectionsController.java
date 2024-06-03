package com.e2i1.linkeepserver.domain.collections.controller;

import static com.e2i1.linkeepserver.common.constant.PageConst.DEFAULT_PAGE_SIZE;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.collections.dto.*;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CollectionsController {
    private final CollectionsBusiness collectionsBusiness;

    @GetMapping("/collections/search")
    public ResponseEntity<List<CollectionResDTO>> searchCollection(@RequestParam String search,@UserSession UsersEntity user){
        return ResponseEntity.ok(null);

    }

    @GetMapping("/collections")
    public ResponseEntity<CollectionResPagingDTO> getUserCollectionList(@RequestParam(value = "lastId", required = false) Long lastId,
        @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size, @UserSession UsersEntity user){
        return ResponseEntity.ok(collectionsBusiness.getUserCollection(lastId, size, user));
    }

    @PostMapping("/collections")
    public ResponseEntity<String> insertCollection(@RequestPart(value = "image", required = false) MultipartFile imgFile,@Valid @RequestPart CollectionReqDTO collectionReqDTO, @UserSession UsersEntity user){
        log.info("{}",collectionReqDTO);
        collectionsBusiness.insert(imgFile,collectionReqDTO,user);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/collection-names")
    public ResponseEntity<List<CollectionTitleResDTO>> getCollectionTitle(@UserSession UsersEntity user){

        return ResponseEntity.ok(collectionsBusiness.getTitle(user));
    }

    @GetMapping("/collections/{collectionId}")
    public ResponseEntity<CollectionUserResDTO> getCollection(@PathVariable Long collectionId, @UserSession UsersEntity user){

        return ResponseEntity.ok(collectionsBusiness.getCollection(collectionId, user));
    }

    @PostMapping("/collections/like")
    public ResponseEntity<HashMap<String,Long>> countLike(@RequestBody CollectionLikeReqDTO likeCollection,@UserSession UsersEntity user){
        HashMap<String,Long> result = new HashMap<>();
        Long numOfLikes = collectionsBusiness.updateNumOfLikes(likeCollection.getCollectionId(),user, likeCollection.isFlag());
        result.put("numOfLikes", numOfLikes);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/collections/like")
    public ResponseEntity<List<CollectionResDTO>> getUserLikeCollectionList(@UserSession UsersEntity user){

        return ResponseEntity.ok(collectionsBusiness.getUserLikeCollection(user));
    }

}
