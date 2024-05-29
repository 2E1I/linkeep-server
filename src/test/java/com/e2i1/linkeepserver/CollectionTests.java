package com.e2i1.linkeepserver;

import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.users.business.UsersBusiness;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class CollectionTests {

  @Autowired
  private UsersBusiness usersBusiness;

  @Autowired
  private UsersService usersService;

  @Test
  public void 팔로잉_목록(){
    UsersEntity user= usersService.findById(2L);
    for(FriendsEntity f : user.getFollowerList())
      System.out.println("id : "+f.getId());
  }



}
