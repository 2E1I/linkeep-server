package com.e2i1.linkeepserver.domain.links.controller;


import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.service.LinkElasticService;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Optional<LinkDocument>> getDocument(@PathVariable String id) {
        return ResponseEntity.ok(linkElasticService.getLink(id));
    }



    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchTitle(@RequestParam String title) {
        return ResponseEntity.ok(linkElasticService.searchTitle(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkDocument> updateTitle(@PathVariable String id, @RequestBody LinkDocument document) {
        document.setId(id);
        return ResponseEntity.ok(linkElasticService.updateTitle(document, id));
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable String id) {
        linkElasticService.deleteDocument(id);
    }
}
