package com.e2i1.linkeepserver.domain.collaborators.service;

import com.e2i1.linkeepserver.domain.collaborators.repository.CollaboratorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollaboratorsService {
    private final CollaboratorsRepository collaboratorsRepository;

}
