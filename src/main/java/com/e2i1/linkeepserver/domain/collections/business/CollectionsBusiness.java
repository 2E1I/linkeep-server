package com.e2i1.linkeepserver.domain.collections.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collaborators.converter.CollaboratorsConverter;
import com.e2i1.linkeepserver.domain.collaborators.dto.CollaboratorResDTO;
import com.e2i1.linkeepserver.domain.collaborators.entity.CollaboratorsEntity;
import com.e2i1.linkeepserver.domain.collaborators.entity.Role;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import com.e2i1.linkeepserver.domain.collections.converter.CollectionsConverter;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionLinkDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionReqDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionResDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionResPagingDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionTitleResDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionUserResDTO;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.image.service.S3ImageService;
import com.e2i1.linkeepserver.domain.likeothers.converter.LikeOthersConverter;
import com.e2i1.linkeepserver.domain.likeothers.entity.LikeOthersEntity;
import com.e2i1.linkeepserver.domain.likeothers.service.LikeOthersService;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.service.LinksService;
import com.e2i1.linkeepserver.domain.tags.converter.TagsConverter;
import com.e2i1.linkeepserver.domain.tags.entity.TagsEntity;
import com.e2i1.linkeepserver.domain.tags.service.TagsService;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Business
@RequiredArgsConstructor
public class CollectionsBusiness {

  private final CollectionsService collectionsService;
  private final LinksService linksService;
  private final TagsService tagsService;
  private final CollectionsConverter collectionsConverter;
  private final LinksConverter linksConverter;
  private final CollaboratorsService collaboratorsService;
  private final CollaboratorsConverter collaboratorsConverter;
  private final TagsConverter tagsConverter;
  private final UsersService usersService;
  private final LikeOthersService likeOthersService;
  private final LikeOthersConverter likeOthersConverter;
  private final S3ImageService s3ImageService;

  public CollectionUserResDTO getCollection(Long id, UsersEntity user) {
    CollectionsEntity collection = collectionsService.findByIdWithThrow(id);
    List<LinksEntity> linkList = linksService.findByCollections(collection);
    List<CollectionLinkDTO> linkDTOList = linkList.stream().map(linksConverter::toCollectionLinkDTO)
        .toList();
    boolean isLike = likeOthersService.getIsLike(collection, user);
    List<TagsEntity> tagList = tagsService.findByCollection(collection);
    List<String> tagDTOList = tagList.stream().map(TagsEntity::getTagName)
        .collect(Collectors.toList());
      return collectionsConverter.toCollectionUserResDTO(collection,
        linkDTOList, tagDTOList,isLike);
  }
  public List<CollectionResDTO> getUserCollection(UsersEntity user){

    List<CollectionsEntity> collectionList = collaboratorsService.findCollectionByUser(user);

    List<CollectionResDTO> collectionResList =  collectionList.stream().map(collectionsEntity -> {
      List<String> tagList = tagsService.findTagNameByCollection(collectionsEntity);
      List<CollaboratorsEntity> collaboratorList = collaboratorsService.findByCollection(collectionsEntity);
      List<CollaboratorResDTO> collaboratorRoleList = collaboratorList.stream().map(collaborators -> {
        return collaboratorsConverter.toCollaboratorResDTO(collaborators, collaborators.getUser());
      }).toList();
      boolean isLike = likeOthersService.getIsLike(collectionsEntity,user);
      return collectionsConverter.toCollectionResDTO(collectionsEntity,isLike,tagList,collaboratorRoleList);
    }).collect(Collectors.toList());

    return collectionResList;
//    boolean hasNext = collectionResList.size() > size;
//    if (hasNext) collectionResList = collectionResList.subList(0, size);
//    return collectionsConverter.toCollectionResPagingDTO(collectionResList,hasNext);
  }

  public List<CollectionTitleResDTO> getTitle(UsersEntity user){
    List<CollectionsEntity> collectionList = collaboratorsService.findCollectionByUser(user);

    return collectionList.stream().map(collectionsConverter::toCollectionTitleResDTO).toList();
  }

  @Transactional
  public Long updateNumOfLikes(Long collectionId,UsersEntity user, boolean isFlag){
    CollectionsEntity collection = collectionsService.findByIdWithThrow(collectionId);

    if(isFlag){
      LikeOthersEntity likeOther = likeOthersConverter.toLikeOthersEntity(collection,user);
      likeOthersService.updateLike(likeOther);
      collection.addLikes();
    }
    else{
      LikeOthersEntity likeOther = likeOthersService.findByCollectionAndUser(collection,user);
      likeOthersService.deleteLike(likeOther);
      collection.deleteLikes();

    }

    return collection.getNumOfLikes();
  }

  @Transactional
  public void insert(MultipartFile imgFile, CollectionReqDTO req, UsersEntity user){
    String imgUrl = null;
    if (imgFile != null && !imgFile.isEmpty()) {
      imgUrl = s3ImageService.upload(imgFile);
    }

    CollectionsEntity collection = collectionsConverter.toEntity(req,imgUrl);
    collectionsService.insert(collection);
    CollaboratorsEntity owner = collaboratorsConverter.toEntity(collection,user, Role.OWNER);
    collaboratorsService.insert(owner);
    for(Long userId : req.getCollaborators()){
      UsersEntity collaborator = usersService.findById(userId);
      CollaboratorsEntity coworker = collaboratorsConverter.toEntity(collection,collaborator, Role.COWORKER);
      collaboratorsService.insert(coworker);
    }

    for(String name : req.getTags()){
      TagsEntity tag = tagsConverter.toEntity(name, collection);
      tagsService.insert(tag);
    }

  }

  public CollectionResPagingDTO getUserLikeCollection(Long lastId, Integer size, UsersEntity user) {

    if (lastId == null) {
      lastId = Long.MAX_VALUE; // lastId가 null인 경우 가능한 가장 큰 ID부터 시작
    }
    Pageable pageable = PageRequest.of(0, size+1);
    List<CollectionsEntity> collectionList = likeOthersService.findCollectionByUser(lastId,user,pageable);

    List<CollectionResDTO> collectionResList =  collectionList.stream().map(collectionsEntity -> {
      List<String> tagList = tagsService.findTagNameByCollection(collectionsEntity);
      List<CollaboratorsEntity> collaboratorList = collaboratorsService.findByCollection(collectionsEntity);
      List<CollaboratorResDTO> collaboratorRoleList = collaboratorList.stream().map(collaborators -> {
        return collaboratorsConverter.toCollaboratorResDTO(collaborators, collaborators.getUser());
      }).toList();
      boolean isLike = likeOthersService.getIsLike(collectionsEntity,user);
      return collectionsConverter.toCollectionResDTO(collectionsEntity,isLike,tagList,collaboratorRoleList);
    }).collect(Collectors.toList());

    boolean hasNext = collectionResList.size() > size;
    if (hasNext) collectionResList = collectionResList.subList(0, size);
    return collectionsConverter.toCollectionResPagingDTO(collectionResList,hasNext);
  }
}
