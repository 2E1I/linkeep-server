package com.e2i1.linkeepserver.domain.tags.service;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import com.e2i1.linkeepserver.domain.tags.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagsService {
    private final TagsRepository tagsRepository;

    public List<TagsEntity> findByCollection(CollectionsEntity collection){
        return tagsRepository.findTagsEntitiesByCollection(collection);
    }

  public void insert(TagsEntity tag) {
      tagsRepository.save(tag);
  }

  public List<String> findTagNameByCollection(CollectionsEntity collection) {
      return tagsRepository.findTagNameByCollection(collection);
  }

  public void deleteAllById(List<String> deleteTags,Long collectionId){
        tagsRepository.deleteAllByUserIdAndCollectionIdInBatch(deleteTags,collectionId);

  }

    public void insertAll(List<TagsEntity> tags) {
        tagsRepository.saveAll(tags);
    }
}
