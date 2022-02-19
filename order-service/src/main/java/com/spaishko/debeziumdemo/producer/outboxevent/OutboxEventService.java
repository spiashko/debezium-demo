package com.spaishko.debeziumdemo.producer.outboxevent;


import com.spaishko.debeziumdemo.producer.domainevent.DomainEvent;

public interface OutboxEventService {

    void persistEvent(DomainEvent<?> event);

}
