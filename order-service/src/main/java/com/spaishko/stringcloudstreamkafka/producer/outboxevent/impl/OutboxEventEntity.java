package com.spaishko.stringcloudstreamkafka.producer.outboxevent.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.spaishko.stringcloudstreamkafka.producer.domainevent.AggregateType;
import com.spaishko.stringcloudstreamkafka.producer.domainevent.EventType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outbox_event")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
class OutboxEventEntity {

    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    @Convert(converter = AggregateTypeConverter.class)
    @NotNull
    @Column(name = "aggregate_type")
    private AggregateType aggregateType;

    @NotNull
    @Column(name = "aggregate_id")
    private UUID aggregateId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @NotNull
    @Column(name = "event_timestamp")
    private LocalDateTime eventTimestamp;

    @NotNull
    @Type(type = "jsonb")
    @Column(name = "payload")
    private JsonNode payload;
}
