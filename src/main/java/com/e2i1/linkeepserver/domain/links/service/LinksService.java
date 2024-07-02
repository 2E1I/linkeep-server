package com.e2i1.linkeepserver.domain.links.service;


import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.collections.entity.CollectionsEntity;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.repository.LinksRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LinksService {

    private final LinksRepository linksRepository;

    public void save(LinksEntity link) {
        linksRepository.save(link);
    }


    public List<LinksEntity> findByCollections(CollectionsEntity collections) {
        return linksRepository.findLinksEntitiesByCollection(collections);
    }

    @Transactional
    public LinksEntity findOneById(Long linkId, Long userId) {
        LinksEntity link = linksRepository.findFirstByIdOrderByIdDesc(linkId)
            .orElseThrow(() -> new ApiException(ErrorCode.LINK_NOT_FOUND));

        // 현재 로그인된 유저의 링크가 아닐 경우에만 조회 수 증가
        if (!userId.equals(link.getUser().getId())) {
            // link 객체는 linksRepository 통해서 가져온거라 현재 영속성 컨텍스트 안에 있음
            // 이렇게 수정만해도 transaction 끝날 때, dirty checking을 통해 자동으로 DB에 수정사항 반영된다.
            link.updateView();
        }

        return link;
    }


    public List<LinksEntity> searchLinks(String keyword, Long view, Long lastId, Pageable pageable) {
        return linksRepository.findByTitleOrDescriptionContainingKeyword(keyword, view, lastId, pageable);
    }

    public List<LinksEntity> findByUserId(Long userId, Long lastId, int size) {
        return linksRepository.findByUserIdAndIdLessThan(userId, lastId, size);
    }

    public void deleteLink(LinksEntity link) {
        linksRepository.delete(link);
    }
}
