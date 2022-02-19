package com.spaishko.debeziumdemo.producer.outboxevent.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaishko.debeziumdemo.producer.domainevent.DomainEvent;
import com.spaishko.debeziumdemo.producer.outboxevent.OutboxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class OutboxEventServiceImpl implements OutboxEventService {

    private final OutboxEventRepository repository;
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void persistEvent(DomainEvent<?> event) {
        OutboxEventEntity outboxEventEntity = OutboxEventEntity.builder()
                .aggregateType(event.getAggregateType())
                .aggregateId(event.getAggregateId())
                .eventType(event.getEventType())
                .eventTimestamp(event.getTimestamp())
                .payload(objectMapper.convertValue(event.getDetails(), JsonNode.class))
                .build();
        repository.save(outboxEventEntity);
    }
}
