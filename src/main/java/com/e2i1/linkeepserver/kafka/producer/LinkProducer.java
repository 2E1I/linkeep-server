package com.e2i1.linkeepserver.kafka.producer;

import com.e2i1.linkeepserver.domain.links.entity.LinksEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LinkProducer {

    private final KafkaTemplate<String, LinksEntity> linkKafkaTemplate;

    public void save(String topic, LinksEntity linkEntity) {
        log.info("========== link 저장 메시지 발행 link = {}", linkEntity);
        linkKafkaTemplate.send(topic, linkEntity);
    }

    public void edit(String topic, LinksEntity linkEntity) {
        log.info("========== link 수정 메시지 발행 link = {}", linkEntity);
        linkKafkaTemplate.send(topic, linkEntity);
    }

    public void delete(String topic, LinksEntity linkEntity) {
        log.info("========== link 삭제 메시지 발행 link = {}", linkEntity);
        linkKafkaTemplate.send(topic, linkEntity);
    }

}
