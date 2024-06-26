package com.e2i1.linkeepserver.domain.links.controller;


import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.service.LinkElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/links-elastic")
public class LinkElasticController {

    private final LinkElasticService linkElasticService;


    @GetMapping("/findAll")
    public ResponseEntity<Iterable<LinkDocument>> getAllLinks() {
        return ResponseEntity.ok(linkElasticService.getLinks());
    }

    @PostMapping
    public ResponseEntity<LinkDocument> createDocument(@RequestBody LinkDocument document) {
        return ResponseEntity.ok(linkElasticService.insertLink(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LinkDocument>> getDocument(@PathVariable Long id) {
        return ResponseEntity.ok(linkElasticService.getLink(id));
    }



    @GetMapping("/search")
    public ResponseEntity<List<LinkDocument>> searchTitle(@RequestParam String title) {
        return ResponseEntity.ok(linkElasticService.search(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkDocument> updateTitle(@PathVariable Long id, @RequestBody LinkDocument document) {
        document.setId(id);
        return ResponseEntity.ok(linkElasticService.updateTitle(document, id));
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        linkElasticService.deleteDocument(id);
    }
}
