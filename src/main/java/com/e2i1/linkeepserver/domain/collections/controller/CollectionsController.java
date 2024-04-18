package com.e2i1.linkeepserver.domain.collections.controller;

import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.collections.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    public ResponseEntity<List<CollectionResDTO>> getUserCollectionList(){
        return ResponseEntity.ok(null);
    }

    @PostMapping("/collections")
    public ResponseEntity<String> insertCollection(@Valid @RequestBody CollectionReqDTO collectionReqDTO){
        return ResponseEntity.ok("success");
    }

    @GetMapping("/collection-names")
    public ResponseEntity<List<CollectionTitleResDTO>> getCollectionTitle(){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/collections/{collectionId}")
    public ResponseEntity<CollectionUserResDTO> getCollection(@PathVariable Long collectionId){

        return ResponseEntity.ok(collectionsBusiness.getCollection(collectionId));

    }

    @PostMapping("/collections/like")
    public ResponseEntity<HashMap<String,Long>> countLike(@RequestBody Long collectionId){
        HashMap<String,Long> result = new HashMap<>();
        Long numOfLikes = 5L;
        result.put("numOfLikes", numOfLikes);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/collections/like")
    public ResponseEntity<CollectionResDTO> getUserLikeCollectionList(){

        return ResponseEntity.ok(null);
    }

}
