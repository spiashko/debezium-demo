package com.spaishko.stringcloudstreamkafka.consumer.stream.idempotency;

import com.spaishko.stringcloudstreamkafka.consumer.messagelog.MessageLogService;
import com.spaishko.stringcloudstreamkafka.consumer.stream.model.MessagePayloadModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Consumer;

@SuppressWarnings("ClassCanBeRecord")
@Slf4j
@RequiredArgsConstructor
public class IdempotentConsumerDecorator implements Consumer<Message<MessagePayloadModel>> {

    private final MessageLogService messageLogService;
    private final Consumer<Message<MessagePayloadModel>> originalConsumer;

    @Transactional
    @Override
    public void accept(Message<MessagePayloadModel> message) {
        UUID eventId = message.getPayload().getEventId();
        if (messageLogService.alreadyProcessed(eventId)) {
            log.info("Event with UUID {} was already retrieved, ignoring it", eventId);
            return;
        }

        originalConsumer.accept(message);

        messageLogService.processed(eventId);
    }
}
