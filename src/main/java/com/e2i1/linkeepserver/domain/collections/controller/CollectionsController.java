package com.e2i1.linkeepserver.domain.collections.controller;

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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CollectionsController {
    private final CollectionsBusiness collectionsBusiness;

    @GetMapping("/collections/search")
    public ResponseEntity<List<SearchCollectionResDTO>> searchCollection(@RequestParam String search){
        return ResponseEntity.ok(null);

    }

    @GetMapping("/collections")
    public ResponseEntity<List<CollectionResDTO>> getUserCollectionList(@UserSession UsersEntity user){
        return ResponseEntity.ok(collectionsBusiness.getUserCollection(user));
    }

    @PostMapping("/collections")
    public ResponseEntity<String> insertCollection(@Valid @RequestBody CollectionReqDTO collectionReqDTO, @UserSession UsersEntity user){
        log.info("{}",collectionReqDTO);
        collectionsBusiness.insert(collectionReqDTO,user);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/collection-names")
    public ResponseEntity<List<CollectionTitleResDTO>> getCollectionTitle(@UserSession UsersEntity user){

        return ResponseEntity.ok(collectionsBusiness.getTitle(user));
    }

    @GetMapping("/collections/{collectionId}")
    public ResponseEntity<CollectionUserResDTO> getCollection(@PathVariable Long collectionId){

        return ResponseEntity.ok(collectionsBusiness.getCollection(collectionId));

    }

    @PostMapping("/collections/like")
    public ResponseEntity<HashMap<String,Long>> countLike(@RequestBody CollectionLikeReqDTO likecollection){
        HashMap<String,Long> result = new HashMap<>();
        Long numOfLikes = collectionsBusiness.updateNumOfLikes(likecollection.getCollectionId());
        result.put("numOfLikes", numOfLikes);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/collections/like")
    public ResponseEntity<CollectionResDTO> getUserLikeCollectionList(){


        return ResponseEntity.ok(null);
    }

}
