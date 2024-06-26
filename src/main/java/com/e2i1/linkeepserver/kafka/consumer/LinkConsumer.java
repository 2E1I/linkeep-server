package com.e2i1.linkeepserver.kafka.consumer;

import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import com.e2i1.linkeepserver.domain.links.service.LinkElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.e2i1.linkeepserver.common.constant.KafkaConst.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class LinkConsumer {

    private final LinkElasticService linkElasticService;
    private final LinksConverter linksConverter;

    @KafkaListener(topics = LINK_SAVE_TOPIC, groupId = LINK_GROUPID, containerFactory = "linkKafkaListenerContainerFactory")
    public void save(@Payload LinksEntity link) {
        log.info("========== START saving link in Elasticsearch DB!!");

        LinkDocument document = linksConverter.toDocument(link);
        linkElasticService.insertLink(document);

        log.info("========== END saving link in Elasticsearch DB!!");
    }

    @KafkaListener(topics = LINK_EDIT_TOPIC, groupId = LINK_GROUPID, containerFactory = "linkKafkaListenerContainerFactory")
    public void edit(@Payload LinksEntity link) {
        log.info("========== START EDITING link in Elasticsearch DB!!");

        LinkDocument document = linksConverter.toDocument(link);
        linkElasticService.edit(document);

        log.info("========== END EDITING link in Elasticsearch DB!!");
    }

    @KafkaListener(topics = LINK_DELETE_TOPIC, groupId = LINK_GROUPID, containerFactory = "linkKafkaListenerContainerFactory")
    public void delete(@Payload LinksEntity link) {
        log.info("========== START DELETING link in Elasticsearch DB!!");

        linkElasticService.deleteDocument(link.getId());

        log.info("========== END DELETING link in Elasticsearch DB!!");
    }



}
