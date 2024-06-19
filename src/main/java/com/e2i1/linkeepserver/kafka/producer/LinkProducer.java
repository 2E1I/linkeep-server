package com.e2i1.linkeepserver.kafka.producer;

import com.e2i1.linkeepserver.domain.links.converter.LinksConverter;
import com.e2i1.linkeepserver.domain.links.entity.LinkDocument;
import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LinkProducer {

    private final KafkaTemplate<String, LinkDocument> linkKafkaTemplate;
    private final LinksConverter linksConverter;

    public void save(String topic, LinksEntity linkEntity) {
        log.info("========== link 저장 메시지 발행 link = {}", linkEntity);

        LinkDocument document = linksConverter.toDocument(linkEntity);

        linkKafkaTemplate.send(topic, document);
    }

}
