package com.spaishko.debeziumdemo.producer.order.events;

import com.spaishko.debeziumdemo.producer.domainevent.AggregateType;
import com.spaishko.debeziumdemo.producer.domainevent.DomainEvent;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public abstract class OrderEvent<DT> extends DomainEvent<DT> {

    public OrderEvent(@NotNull DT details, @NotNull UUID aggregateId) {
        super(details, aggregateId);
    }

    @Override
    public AggregateType getAggregateType() {
        return AggregateType.ORDER;
    }

}
