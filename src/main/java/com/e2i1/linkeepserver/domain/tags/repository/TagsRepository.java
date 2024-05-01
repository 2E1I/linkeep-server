package com.e2i1.linkeepserver.domain.tags.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TagsRepository extends JpaRepository<TagsEntity,Long> {
    public List<TagsEntity> findTagsEntitiesByCollection(CollectionsEntity collection);

    List<String> findTagNameBycollection(CollectionsEntity collection);
}
