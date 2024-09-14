package com.e2i1.linkeepserver.domain.tags.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.tags.converter.TagsConverter;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import com.e2i1.linkeepserver.domain.tags.service.TagsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Business
@RequiredArgsConstructor
public class  TagsBusiness {
    private final TagsService tagsService;
    private final TagsConverter tagsConverter;
    private final CollaboratorsService collaboratorsService;
    private final CollectionsService collectionsService;

    @Transactional
    public void editTags(Long userId, Long collectionId, List<String> insertTags, List<String> deleteTags) {
        Long ownerId = collaboratorsService.findCollectionOwner(collectionId);
        if (Objects.equals(ownerId, userId)){
            if(deleteTags!=null){
                tagsService.deleteAllById(deleteTags,collectionId);
            }
            if(insertTags!=null){
                CollectionsEntity collection = collectionsService.findByIdWithThrow(collectionId);
                List<TagsEntity> tags = insertTags.stream().map(insertTag -> {
                    return tagsConverter.toEntity(insertTag,collection);
                }).toList();
                tagsService.insertAll(tags);
            }
        }


    }
}
