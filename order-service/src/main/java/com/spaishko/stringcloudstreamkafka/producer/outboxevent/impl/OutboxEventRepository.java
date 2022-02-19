package com.spaishko.stringcloudstreamkafka.producer.outboxevent.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, UUID> {

}
