package com.e2i1.linkeepserver.domain.users.service;

import com.e2i1.linkeepserver.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.e2i1.linkeepserver.common.error.ErrorCode.INDEX_OUT_OF_RANGE;
import static com.e2i1.linkeepserver.common.error.ErrorCode.NULL_POINT;

@Service
@RequiredArgsConstructor
public class RecentSearchService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String RECENT_SEARCHES_KEY_PREFIX = "recent_searches:";
    private static final int MAX_RECENT_SEARCHES = 10;
    private static final String DELETE_MARKER = "__DELETE__";


    public void addSearchTerm(Long userId, String keyword) {
        String key = RECENT_SEARCHES_KEY_PREFIX + userId;

        List<String> searchList = redisTemplate.opsForList().range(key, 0, -1);

        if (searchList == null) {
            throw new ApiException(NULL_POINT, "해당 유저는 최근 검색 기록이 없습니다.");
        }

        // keyword가 이미 최근 검색어 목록에 있다면 중복 저장하지 않고 가장 앞으로 가져오기 위해 삭제
        if (searchList.contains(keyword)) {
            redisTemplate.opsForList().remove(key, 0, keyword);
        }

        redisTemplate.opsForList().leftPush(key, keyword);

        redisTemplate.opsForList().trim(key, 0, MAX_RECENT_SEARCHES - 1);
    }

    public List<String> getRecentSearch(Long userId) {
        String key = RECENT_SEARCHES_KEY_PREFIX + userId;
        return redisTemplate.opsForList().range(key, 0, -1);

    }

    public void deleteRecentSearch(Long userId, int index) {
        String key = RECENT_SEARCHES_KEY_PREFIX + userId;
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        Long listSize = listOps.size(key);

        if (listSize == null) {
            throw new ApiException(NULL_POINT, "해당 유저는 최근 검색 기록이 없습니다.");
        }
        if (index >= listSize || index < 0) {
            throw new ApiException(INDEX_OUT_OF_RANGE);
        }

        listOps.set(key, index, DELETE_MARKER);
        listOps.remove(key, 0, DELETE_MARKER);  //list 개수 10개 고정이기에 전체 탐색 오버헤드 작음
    }


}
