package com.e2i1.linkeepserver.domain.collections.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collections.converter.CollectionsConverter;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionLinkDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionUserResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.service.LinksService;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import com.e2i1.linkeepserver.domain.tags.service.TagsService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class CollectionsBusiness {

  private final CollectionsService collectionsService;
  private final LinksService linksService;
  private final TagsService tagsService;
  private final CollectionsConverter collectionsConverter;
  private final LinksConverter linksConverter;

  public CollectionUserResDTO getCollection(Long id) {
    CollectionsEntity collection = collectionsService.findByIdWithThrow(id);
    List<LinksEntity> linkList = linksService.findByCollections(collection);
    List<CollectionLinkDTO> linkDTOList = linkList.stream().map(linksConverter::toCollectionLinkDTO)
        .toList();
    List<TagsEntity> tagList = tagsService.findByCollection(collection);
    List<String> tagDTOList = tagList.stream().map(TagsEntity::getTagName)
        .collect(Collectors.toList());
    CollectionUserResDTO result = collectionsConverter.toCollectionUserResDTO(collection,
        linkDTOList, tagDTOList);
    return result;
  }
}
