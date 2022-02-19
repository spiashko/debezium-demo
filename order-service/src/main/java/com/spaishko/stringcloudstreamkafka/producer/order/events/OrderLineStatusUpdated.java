package com.spaishko.stringcloudstreamkafka.producer.order.events;

import com.spaishko.stringcloudstreamkafka.producer.domainevent.EventType;
import com.spaishko.stringcloudstreamkafka.producer.order.OrderLineStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@ToString
@Getter
public class OrderLineStatusUpdated extends OrderEvent<OrderLineStatusUpdated.OrderUpdatedDetails> {

    public OrderLineStatusUpdated(@NotNull OrderUpdatedDetails details, @NotNull UUID aggregateId) {
        super(details, aggregateId);
    }

    @Override
    public EventType getEventType() {
        return EventType.ORDER_LINE_STATUS_UPDATED;
    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderUpdatedDetails {
        @NonNull
        private OrderLineStatus oldOrderLineStatus;
        @NonNull
        private OrderLineStatus newOrderLineStatus;
    }
}