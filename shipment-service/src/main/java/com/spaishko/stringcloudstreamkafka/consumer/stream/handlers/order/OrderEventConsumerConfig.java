package com.spaishko.stringcloudstreamkafka.consumer.stream.handlers.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaishko.stringcloudstreamkafka.consumer.messagelog.MessageLogService;
import com.spaishko.stringcloudstreamkafka.consumer.stream.idempotency.IdempotentConsumerDecorator;
import com.spaishko.stringcloudstreamkafka.consumer.stream.model.EventType;
import com.spaishko.stringcloudstreamkafka.consumer.stream.model.MessagePayloadModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.time.Instant;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
class OrderEventConsumerConfig {

    @Bean
    Consumer<Message<MessagePayloadModel>> orderEventConsumer(OrderEventHandler handler,
                                                              ObjectMapper objectMapper,
                                                              MessageLogService messageLogService) {
        return new IdempotentConsumerDecorator(
                messageLogService,
                new OrderEventConsumer(handler, objectMapper)
        );
    }

    @RequiredArgsConstructor
    @Slf4j
    static class OrderEventConsumer implements Consumer<Message<MessagePayloadModel>> {

        private final OrderEventHandler handler;
        private final ObjectMapper objectMapper;

        @Override
        public void accept(Message<MessagePayloadModel> m) {
            MessagePayloadModel payload = m.getPayload();
            EventType eventType = payload.getEventType();

            switch (eventType) {
                case ORDER_CREATED -> handleOrderCreated(m);
                case ORDER_LINE_STATUS_UPDATED -> handleOrderLineStatusUpdated(m);
            }
        }

        @SneakyThrows
        private void handleOrderLineStatusUpdated(Message<MessagePayloadModel> m) {
            MessagePayloadModel payload = m.getPayload();
            String eventPayload = payload.getPayload();
            OrderLineStatusUpdatedEvent event = objectMapper.readValue(eventPayload, OrderLineStatusUpdatedEvent.class);
            Instant eventTimestamp = Instant.ofEpochMilli(m.getPayload().getEventTimestamp() / 1000);

            handler.onOrderLineStatusUpdated(event, payload.getAggregateId(), eventTimestamp);
        }

        @SneakyThrows
        private void handleOrderCreated(Message<MessagePayloadModel> m) {
            String eventPayload = m.getPayload().getPayload();
            OrderCreatedEvent event = objectMapper.readValue(eventPayload, OrderCreatedEvent.class);
            handler.onOrderCreatedEvent(event);
        }
    }

}
