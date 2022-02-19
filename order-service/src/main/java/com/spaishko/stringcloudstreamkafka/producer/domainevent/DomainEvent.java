package com.spaishko.stringcloudstreamkafka.producer.domainevent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public abstract class DomainEvent<DT> {

    @NotNull
    @PastOrPresent
    private final LocalDateTime timestamp = LocalDateTime.now();
    @NotNull
    private final DT details;
    @NotNull
    private final UUID aggregateId;

    @NotNull
    public abstract AggregateType getAggregateType();

    @NotNull
    public abstract EventType getEventType();

}
