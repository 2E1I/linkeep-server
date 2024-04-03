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
    public ResponseEntity<CollectionUserResDTO> getCollection(@PathVariable String collectionId){

        return ResponseEntity.ok(null);

    }

    @GetMapping("/collections/like")
    public ResponseEntity<HashMap<String,Long>> getUserLikeCollectionList(){
        HashMap<String,Long> result = new HashMap<>();
        result.put("numOfLikes", 22L);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/collections/like")
    public ResponseEntity<CollectionResDTO> countLike(@RequestBody Long collectionId){
        return ResponseEntity.ok(null);
    }

}
