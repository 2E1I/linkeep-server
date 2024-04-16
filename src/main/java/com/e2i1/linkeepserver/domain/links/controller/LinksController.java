package com.e2i1.linkeepserver.domain.links.controller;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.links.business.LinksBusiness;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkResDTO;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/links")
public class LinksController {
    private final LinksBusiness linksBusiness;

    @GetMapping()
    public ResponseEntity<SearchLinkResDTO> searchLink(@RequestParam("search") String search) {

        return ResponseEntity.ok(null);
    }

    @PostMapping()
    public ResponseEntity<String> saveLink(@RequestBody @Valid LinkReqDTO linkReqDTO, @UserSession UsersEntity user) {
        log.info("{}", linkReqDTO);
        linksBusiness.save(linkReqDTO, user);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{linkId}")
    public ResponseEntity<LinkResDTO> getLink(@PathVariable String linkId) {

        return ResponseEntity.ok(null);
    }

    @PatchMapping("/views")
    public ResponseEntity<HashMap<String, Long>> plusView(@RequestBody Long linkId) {
        HashMap<String, Long> result = new HashMap<>();
        result.put("numOfViews", 22L);
        return ResponseEntity.ok(result);
    }
}
