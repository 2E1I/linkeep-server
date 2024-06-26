package com.e2i1.linkeepserver.domain.links.business;

import static com.e2i1.linkeepserver.common.constant.KafkaConst.*;
import static com.e2i1.linkeepserver.common.constant.PageConst.*;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collaborators.service.CollaboratorsService;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.collections.service.CollectionsService;
import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.dto.LinkEditReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.links.dto.LinkResDTO;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkDTO;
import com.e2i1.linkeepserver.domain.links.dto.SearchLinkResDTO;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.service.LinksService;
import com.e2i1.linkeepserver.domain.users.dto.LinkHomeResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.kafka.producer.LinkProducer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.e2i1.linkeepserver.domain.users.service.RecentSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Business
@RequiredArgsConstructor
public class LinksBusiness {

    private final LinksService linksService;
    private final LinksConverter linksConverter;

    private final CollectionsService collectionsService;

    private final CollaboratorsService collaboratorsService;

    private final RecentSearchService recentSearchService;

    private final LinkProducer linkProducer;

    // 사전에 정규 표현식 컴파일 해놓고 재사용하기
    final Pattern BLANK_PATTERN = Pattern.compile("\\s+");

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

        linkProducer.save(LINK_SAVE_TOPIC, linkEntity);
    }

    @Transactional
    public void editLink(Long userId, Long linkId, LinkEditReqDTO editReq) {
        LinksEntity link = validateLinkOwner(userId, linkId);

        // editReq 바탕으로 링크 수정하기
        link.editLink(editReq.getTitle(), editReq.getDescription(), editReq.getUrl());

        linkProducer.edit(LINK_EDIT_TOPIC, link);
    }

    @Transactional
    public void deleteLink(Long userId, Long linkId) {
        LinksEntity link = validateLinkOwner(userId, linkId);

        linksService.deleteLink(link);
        linkProducer.delete(LINK_DELETE_TOPIC, link);
    }

    /**
     * 링크 주인인지 검증, 주인 아니면 예외 던짐
     */
    private LinksEntity validateLinkOwner(Long userId, Long linkId) {
        LinksEntity link = linksService.findOneById(linkId, userId);

        // 링크 주인인지 검증
        if (!link.getUser().getId().equals(userId)) {
            throw new ApiException(ErrorCode.LINK_ACCESS_DENIED);
        }
        return link;
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
    public SearchLinkResDTO searchLinks(Long userId, String keyword, Long view, Long lastId, Integer size) {
        // redis에 유저의 최근 검색어 목록에 검색어 저장하기
        recentSearchService.addSearchTerm(userId, keyword);

        // 검색어를 공백 제외하고 하나의 문자열로 변환
        keyword = BLANK_PATTERN.matcher(keyword).replaceAll("").toLowerCase();

        // size 없으면 default size 값 넣기
        size = Optional.ofNullable(size).orElseGet(() -> Integer.valueOf(DEFAULT_PAGE_SIZE));
        Pageable pageable = PageRequest.of(0, size + 1);

        // lastId, view가 null인 경우는 첫 페이징 조회시
        lastId = Optional.ofNullable(lastId).orElse(Long.MAX_VALUE);
        view = Optional.ofNullable(view).orElse(Long.MAX_VALUE);

        // lastId보다 작은 링크를 size+1 개수만큼 가져오기
        // size+1 인 이유 : 다음 페이지가 있는지 확인하기 위해
        List<LinksEntity> linkList = linksService.searchLinks(keyword, view, lastId, pageable);
        
        // size+1개 만큼 데이터를 못가져왔다면 마지막 페이지인 것
        boolean hasNext = linkList.size() > size;
        if (hasNext) {
            linkList = linkList.subList(0, size);
        }

        List<SearchLinkDTO> searchLinkList = linkList.stream()
            .map(linksConverter::toSearchResponse)
            .collect(Collectors.toList());

        return SearchLinkResDTO.builder()
            .searchLinkList(searchLinkList)
            .hasNext(hasNext)
            .build();
    }

    /**
     * 유저가 저장한 모든 link list 불러오기
     * 최신 순으로 정렬해서
     */
    public List<LinkHomeResDTO> findByUserId(Long userId, Long lastId, int size) {
        Pageable pageable = PageRequest.of(0, size+1);
        List<LinksEntity> linkList = linksService.findByUserId(userId, lastId, pageable);

        return linkList.stream()
            .map(linksConverter::toLinkHomeResponse)
            .collect(Collectors.toList());
    }


}
