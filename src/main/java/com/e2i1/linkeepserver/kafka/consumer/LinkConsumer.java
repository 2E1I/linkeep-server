package com.e2i1.linkeepserver.kafka.consumer;

import static com.e2i1.linkeepserver.common.constant.KafkaConst.LINK_GROUPID;
import static com.e2i1.linkeepserver.common.constant.KafkaConst.LINK_SAVE_TOPIC;

import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.service.LinkElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LinkConsumer {

    private final LinkElasticService linkElasticService;
    @KafkaListener(topics = LINK_SAVE_TOPIC, groupId = LINK_GROUPID, containerFactory = "linkSaveKafkaListenerContainerFactory")
    public void receive(@Payload LinkDocument document) {
        log.info("========== START saving link in Elasticsearch DB!!");

        linkElasticService.insertLink(document);

        log.info("========== END saving link in Elasticsearch DB!!");

    }

}
