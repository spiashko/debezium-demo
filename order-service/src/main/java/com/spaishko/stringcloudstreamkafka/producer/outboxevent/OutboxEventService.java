package com.spaishko.stringcloudstreamkafka.producer.outboxevent;


import com.spaishko.stringcloudstreamkafka.producer.domainevent.DomainEvent;

public interface OutboxEventService {

    void persistEvent(DomainEvent<?> event);

}
