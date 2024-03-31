package com.e2i1.linkeepserver.domain.collaborators.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collaborators.business.CollaboratorsBusiness;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class CollaboratorsConverter {

    private final CollaboratorsBusiness collaboratorsBusiness;
}
