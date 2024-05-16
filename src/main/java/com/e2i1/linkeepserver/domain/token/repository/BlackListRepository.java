package com.e2i1.linkeepserver.domain.token.repository;

import com.e2i1.linkeepserver.domain.token.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    BlackList findByInvalidToken(String token);
}
