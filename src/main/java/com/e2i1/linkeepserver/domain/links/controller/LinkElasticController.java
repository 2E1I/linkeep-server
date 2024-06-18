package com.e2i1.linkeepserver.domain.links.controller;


import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.service.LinkElasticService;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping
    public LinkDocument createDocument(@RequestBody LinkDocument document) {
        return linkElasticService.createDocument(document);
    }

    @GetMapping("/{id}")
    public Optional<LinkDocument> getDocument(@PathVariable String id) {
        return linkElasticService.getDocument(id);
    }

    @GetMapping("/search")
    public Map<String, Object> searchTitle(@RequestParam String title) {
        return linkElasticService.searchTitle(title);
    }

    @PutMapping("/{id}")
    public LinkDocument updateDocument(@PathVariable String id, @RequestBody LinkDocument document) {
        document.setId(id);
        return linkElasticService.updateDocument(document);
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable String id) {
        linkElasticService.deleteDocument(id);
    }
}
