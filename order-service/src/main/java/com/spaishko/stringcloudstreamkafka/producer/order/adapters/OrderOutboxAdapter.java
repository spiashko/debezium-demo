package com.spaishko.stringcloudstreamkafka.producer.order.adapters;

import com.spaishko.stringcloudstreamkafka.producer.order.events.OrderEvent;
import com.spaishko.stringcloudstreamkafka.producer.outboxevent.OutboxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
class OrderOutboxAdapter {

    private final OutboxEventService service;

    @TransactionalEventListener(value = OrderEvent.class, phase = TransactionPhase.BEFORE_COMMIT)
    public void createOutboxEvent(OrderEvent<?> event) {
        service.persistEvent(event);
    }

}
