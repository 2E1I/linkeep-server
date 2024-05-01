package com.e2i1.linkeepserver.domain.links.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.repository.LinksRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LinksBusinessTest {

    @Autowired
    private LinksBusiness linksBusiness;

    @Autowired
    private LinksRepository linksRepository;


    @Test
    public void 동시에_100개_요청() throws InterruptedException {
        int threadCount = 100;
        // 비동기로 실행하는 작업을 단순화하여 사용할 수 있게 도와주는 Java API
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 다른 thread에서 수행 중인 작업이 완료될 때까지 대기할 수 있도록 도와주는 클래스
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try{
                    linksBusiness.findOneById(1L, 2L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        LinksEntity link = linksRepository.findById(1L).orElseThrow();

        assertEquals(100L, link.getNumOfViews());

    }


}