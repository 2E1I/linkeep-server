package com.e2i1.linkeepserver;

import com.e2i1.linkeepserver.domain.collections.business.CollectionsBusiness;
import com.e2i1.linkeepserver.domain.friends.entity.FriendsEntity;
import com.e2i1.linkeepserver.domain.users.business.UsersBusiness;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
@Transactional
public class CollectionTests {

  @Autowired
  private UsersBusiness usersBusiness;

  @Autowired
  private UsersService usersService;

  @Autowired
  CollectionsBusiness collectionsBusiness;

  @Test
  public void 팔로잉_목록(){
    UsersEntity user= usersService.findById(2L);
    for(FriendsEntity f : user.getFollowerList())
      System.out.println("id : "+f.getId());
  }

  @Test
  public void 좋아요_업데이트(){
    int threadCount = 100;

    ExecutorService executorService = Executors.newFixedThreadPool(32);
    UsersEntity user = usersService.findById(2L);

    CountDownLatch latch = new CountDownLatch(threadCount);
    for (int i = 0; i < threadCount; i++) {

      executorService.submit(() -> {
        try{

          System.out.println(collectionsBusiness.updateNumOfLikes(9L,user,false));
        } finally {
          latch.countDown();
        }
      });
    }

  }



}
