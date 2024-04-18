package com.e2i1.linkeepserver.domain.links.controller;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.links.business.LinksBusiness;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkResDTO;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/links")
public class LinksController {

    private final LinksBusiness linksBusiness;

    @GetMapping()
    public ResponseEntity<List<SearchLinkResDTO>> searchLink(
        @RequestParam("search") String searchTerm) {
        log.info("search term is {}", searchTerm);
        List<SearchLinkResDTO> searchLinks = linksBusiness.searchLinks(searchTerm);
        return ResponseEntity.ok(searchLinks);
    }

    @PostMapping()
    public ResponseEntity<String> saveLink(@RequestBody @Valid LinkReqDTO linkReqDTO,
        @UserSession UsersEntity user) {
        log.info("{}", linkReqDTO);
        linksBusiness.save(linkReqDTO, user);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{linkId}")
    public ResponseEntity<LinkResDTO> getLink(@PathVariable Long linkId,
        @UserSession UsersEntity user) {
        LinkResDTO response = linksBusiness.findOneById(linkId, user.getId());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/views")
    public ResponseEntity<HashMap<String, Long>> plusView(@RequestBody Long linkId) {
        HashMap<String, Long> result = new HashMap<>();
        result.put("numOfViews", 22L);
        return ResponseEntity.ok(result);
    }
}
