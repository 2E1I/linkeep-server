package com.e2i1.linkeepserver.domain.tags.converter;

import com.e2i1.linkeepserver.common.annotation.Converter;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class TagsConverter {

  public TagsEntity toEntity(String name, CollectionsEntity collection) {
    return TagsEntity.builder()
        .tagName(name)
        .collection(collection).build();
  }
}
