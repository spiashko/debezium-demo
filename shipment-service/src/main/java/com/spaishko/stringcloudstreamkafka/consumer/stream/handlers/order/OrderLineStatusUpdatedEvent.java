package com.spaishko.stringcloudstreamkafka.consumer.stream.handlers.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
class OrderLineStatusUpdatedEvent {

    private OrderLineStatus oldOrderLineStatus;
    private OrderLineStatus newOrderLineStatus;

}