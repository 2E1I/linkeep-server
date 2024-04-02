package com.e2i1.linkeepserver.domain.links.controller;

import com.e2i1.linkeepserver.domain.links.business.LinksBusiness;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkResDTO;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/links")
public class LinksController {
    private final LinksBusiness linksBusiness;

    @GetMapping()
    public ResponseEntity<SearchLinkResDTO> searchLink(@RequestParam("search")String search){

        return ResponseEntity.ok(null);
    }
    @PostMapping()
    public ResponseEntity<String> searchLink(@RequestBody LinkReqDTO linkReqDTO){
        return ResponseEntity.ok("success");
    }
    @GetMapping("/{linkId}")
    public ResponseEntity<LinkResDTO> getLink(@PathVariable String linkId){

        return ResponseEntity.ok(null);
    }

    @PostMapping("/view")
    public ResponseEntity<HashMap<String,Long>> countView(@RequestBody Long linkId){
        HashMap<String,Long> result = new HashMap<>();
        result.put("numOfViews", 22L);
        return ResponseEntity.ok(result);
    }
}
