package com.spaishko.stringcloudstreamkafka.consumer.messagelog.impl;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "message_log")
class MessageLog {

    @Id
    @Column(name = "event_id")
    private UUID eventId;

    @NotNull
    @Column(name = "time_of_receiving")
    private LocalDateTime timeOfReceiving;

}
