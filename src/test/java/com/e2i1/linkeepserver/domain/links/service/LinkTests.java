package com.e2i1.linkeepserver.domain.links.service;

import com.e2i1.linkeepserver.domain.links.business.LinksBusiness;
import com.e2i1.linkeepserver.domain.links.dto.LinkReqDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType.SignatureRelevant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LinkTests {
  @Autowired
  private LinksBusiness linksBusiness;


}
