package com.spaishko.stringcloudstreamkafka.consumer.messagelog.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface MessageLogRepository extends JpaRepository<MessageLog, UUID> {
}
