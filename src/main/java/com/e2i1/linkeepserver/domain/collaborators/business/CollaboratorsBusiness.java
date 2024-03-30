package com.e2i1.linkeepserver.domain.collaborators.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class CollaboratorsBusiness {
    private final CollaboratorsService collaboratorsService;

}
