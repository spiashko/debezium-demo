package com.spaishko.debeziumdemo.consumer.stream.handlers.order;

import com.spaishko.debeziumdemo.consumer.shipment.ShipmentEntity;
import com.spaishko.debeziumdemo.consumer.shipment.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
class OrderEventHandler {

    private final ShipmentRepository shipmentRepository;
    private final OrderEventHandlerMapper mapper;

    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent: " + event);
        ShipmentEntity entityToCreate = mapper.map(event.getCreatedOrder());
        shipmentRepository.save(entityToCreate);
    }

    public void onOrderLineStatusUpdated(OrderLineStatusUpdatedEvent event,
                                         UUID orderUuid,
                                         Instant eventTimestamp) {
        log.info("OrderLineStatusUpdatedEvent: " + event);
        log.info("orderUuid: " + orderUuid);
        log.info("eventTimestamp: " + eventTimestamp);
//        if (true) {
//            throw new RuntimeException();
//        }
    }

}
