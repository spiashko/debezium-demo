package com.spaishko.debeziumdemo.producer.order.impl;

import com.spaishko.debeziumdemo.producer.order.*;
import com.spaishko.debeziumdemo.producer.order.events.OrderCreated;
import com.spaishko.debeziumdemo.producer.order.events.OrderLineStatusUpdated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
class OrderManagementServiceImpl implements OrderManagementService {

    private final OrderRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public OrderEntity addOrder(OrderEntity entityToCreate) {

        entityToCreate.getLineItems().forEach(li -> li.setOrderLineStatus(OrderLineStatus.ENTERED));
        OrderEntity entity = repository.save(entityToCreate);

        OrderCreated.OrderCreatedDetails eventDetails =
                OrderCreated.OrderCreatedDetails.builder()
                        .createdOrder(entity)
                        .build();
        eventPublisher.publishEvent(new OrderCreated(eventDetails, entityToCreate.getUuid()));

        return entity;
    }

    @Transactional
    @Override
    public OrderEntity updateOrderLineStatus(UUID orderId, UUID orderLineId, OrderLineStatus newStatus) {

        OrderEntity order = repository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found"));

        for (OrderLineEntity orderLine : order.getLineItems()) {
            if (orderLine.getUuid().equals(orderLineId)) {
                OrderLineStatus oldOrderLineStatus = orderLine.getOrderLineStatus();

                orderLine.setOrderLineStatus(newStatus);
                repository.save(order);

                OrderLineStatusUpdated.OrderUpdatedDetails eventDetails =
                        OrderLineStatusUpdated.OrderUpdatedDetails.builder()
                                .oldOrderLineStatus(oldOrderLineStatus)
                                .newOrderLineStatus(newStatus)
                                .build();
                eventPublisher.publishEvent(new OrderLineStatusUpdated(eventDetails, orderId));

                return order;
            }
        }

        throw new EntityNotFoundException("Order does not contain line with id " + orderLineId);
    }
}
