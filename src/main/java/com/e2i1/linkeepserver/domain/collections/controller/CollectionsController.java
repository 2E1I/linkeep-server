package com.e2i1.linkeepserver.domain.collections.controller;

import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.collections.dto.SearchCollectionResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CollectionsController {
    private final CollectionsBusiness collectionsBusiness;

    @GetMapping("/collections/search")
    public ResponseEntity<List<SearchCollectionResDTO>> getCollectionsKeyword(@RequestParam String search){
        return ResponseEntity.ok(null);

    }



}
