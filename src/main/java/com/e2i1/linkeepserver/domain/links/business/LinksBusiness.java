package com.e2i1.linkeepserver.domain.links.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkResDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.service.LinksService;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Business
@RequiredArgsConstructor
public class LinksBusiness {

    private final LinksService linksService;
    private final LinksConverter linksConverter;

    private final CollectionsService collectionsService;

    private final CollaboratorsService collaboratorsService;

    /**
     * link 저장하기
     * 해당 컬렉션에 권한 있는지 확인 후 저장하기
     */
    public LinksEntity save(LinkReqDTO req, UsersEntity user) {
        /*
        내가 해당 collection의 작업자일 때만 링크 저장 가능
        만약 해당 collection이 존재하지 않거나, 내가 collection의 작업자가 아니라면 예외가 발생한다
         */
        CollectionsEntity collection = collectionsService.findByIdWithThrow(req.getCollectionId());
        collaboratorsService.findByUserIdAndCollectionIdWithThrow(user.getId(), collection.getId());

        LinksEntity linkEntity = linksConverter.toEntity(req, collection, user);
        return linksService.save(linkEntity);
    }

    /**
     * link 단건 조회
     * 현재 로그인된 유저가 생성한 link가 아닌 경우, link 조회수 1증가
     * 즉, 자기가 만든 link는 아무리 조회해도 조회수 증가 안함
     */
    @Transactional
    public LinkResDTO findOneById(Long linkId, Long userId) {
        LinksEntity link = linksService.findOneByIdAndUserId(linkId);

        // 현재 로그인된 유저의 링크가 아닐 경우에만 조회 수 증가
        if (!userId.equals(link.getUser().getId())) {
            // link 객체는 linksService를 통해서 가져온거라 현재 영속성 컨텍스트 안에 있음
            // 이렇게 수정만해도 transaction 끝날 때, dirty checking을 통해 자동으로 DB에 수정사항 반영된다.
            link.updateView();
        }
        return linksConverter.toResponse(link);
    }
}
