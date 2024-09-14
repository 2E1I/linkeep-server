package com.e2i1.linkeepserver.domain.tags.repository;

import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TagsRepository extends JpaRepository<TagsEntity,Long> {
    public List<TagsEntity> findTagsEntitiesByCollection(CollectionsEntity collection);

    @Query("select tagName from TagsEntity where collection=:collection")
    List<String> findTagNameByCollection(@Param("collection") CollectionsEntity collection);

    @Modifying
    @Transactional
    @Query("delete from TagsEntity t where t.tagName in :deleteTags and t.collection.id =:collectionId")
    void deleteAllByUserIdAndCollectionIdInBatch(List<String> deleteTags, Long collectionId);
}
