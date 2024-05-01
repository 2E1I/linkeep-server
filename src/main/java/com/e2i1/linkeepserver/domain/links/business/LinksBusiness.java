package com.e2i1.linkeepserver.domain.links.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkResDTO;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkResDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.service.LinksService;
import com.e2i1.linkeepserver.domain.users.dto.LinkHomeResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.stream.Collectors;
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
     * link 저장하기 해당 컬렉션에 권한 있는지 확인 후 저장하기
     */
    @Transactional
    public void save(LinkReqDTO req, UsersEntity user) {
        /*
        내가 해당 collection의 작업자일 때만 링크 저장 가능
        만약 해당 collection이 존재하지 않거나, 내가 collection의 작업자가 아니라면 예외가 발생한다
         */
        CollectionsEntity collection = collectionsService.findByIdWithThrow(req.getCollectionId());
        collaboratorsService.findByUserIdAndCollectionIdWithThrow(user.getId(), collection.getId());

        LinksEntity linkEntity = linksConverter.toEntity(req, collection, user);
        linksService.save(linkEntity);
    }

    /**
     * link 단건 조회
     * 현재 로그인된 유저가 생성한 link가 아닌 경우, link 조회수 1증가
     * 즉, 자기가 만든 link는 아무리 조회해도 조회수 증가 안함
     */
    public LinkResDTO findOneById(Long linkId, Long userId) {

        while (true) {
            try {
                LinksEntity link = linksService.findOneById(linkId, userId);
                return linksConverter.toResponse(link);
            } catch (Exception e) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    throw new ApiException(ErrorCode.SERVER_ERROR, ex);
                }
            }
        }

    }


    /**
     * 링크 title, description을 조회해 해당 검색어 들어있는 링크 목록 가져오기
     */
    public List<SearchLinkResDTO> searchLinks(String keyword) {
        // 검색어를 공백 제외하고 하나의 문자열로 변환
        keyword = keyword.replaceAll("\\s+", "").toLowerCase();

        List<LinksEntity> linkList = linksService.searchLinks(keyword);

        return linkList.stream()
            .map(linksConverter::toSearchResponse)
            .collect(Collectors.toList());
    }

    /**
     * 유저가 저장한 모든 link list 불러오기
     * 최신 순으로 정렬해서
     */
    public List<LinkHomeResDTO> findByUserId(Long userId) {
        List<LinksEntity> linkList = linksService.findByUserId(userId);

        return linkList.stream()
            .map(linksConverter::toLinkHomeResponse)
            .collect(Collectors.toList());
    }
}
