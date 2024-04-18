package com.e2i1.linkeepserver.domain.links.service;

import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.repository.LinksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class LinksServiceTest {

    @Mock
    private LinksRepository linksRepository;

    @InjectMocks
    private LinksService linksService;

    private LinksEntity link;

//    @BeforeEach
//    public void setLink() {
//
//
//        link = LinksEntity.builder()
//                .user()
//                .title("link title")
//                .url("www.link.com")
//                .description("link description")
//                .collection()
//                .numOfViews(0)
//                .createdAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
//                .build();
//
//    }


}