package com.spaishko.stringcloudstreamkafka.consumer.stream.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MessagePayloadModel {

    private String payload;
    private UUID eventId;
    private Long eventTimestamp;
    private UUID aggregateId;
    private EventType eventType;

}
