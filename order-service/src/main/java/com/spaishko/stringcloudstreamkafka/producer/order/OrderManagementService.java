package com.spaishko.stringcloudstreamkafka.producer.order;

import java.util.UUID;

public interface OrderManagementService {

    OrderEntity addOrder(OrderEntity createRequest);

    OrderEntity updateOrderLineStatus(UUID orderId, UUID orderLineId, OrderLineStatus newStatus);

}
