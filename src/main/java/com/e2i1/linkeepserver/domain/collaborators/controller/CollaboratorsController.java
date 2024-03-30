package com.e2i1.linkeepserver.domain.collaborators.controller;

import com.e2i1.linkeepserver.domain.collaborators.business.CollaboratorsBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CollaboratorsController {
    private final CollaboratorsBusiness collaboratorsBusiness;

}
