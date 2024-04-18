package com.e2i1.linkeepserver;

import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionResDTO;
import com.e2i1.linkeepserver.domain.collections.dto.CollectionUserResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class CollectionTests {

  @Autowired
  private CollectionsBusiness collectionsBusiness;


  @Test
  void 모음집_조회() {
    CollectionUserResDTO result = collectionsBusiness.getCollection(2L);
    System.out.println(result);
  }


}
