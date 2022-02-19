package com.spaishko.stringcloudstreamkafka.consumer.messagelog.impl;

import com.spaishko.stringcloudstreamkafka.consumer.messagelog.MessageLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class MessageLogServiceImpl implements MessageLogService {

    private final MessageLogRepository repository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void processed(UUID eventId) {
        repository.save(new MessageLog(eventId, LocalDateTime.now()));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean alreadyProcessed(UUID eventId) {
        log.debug("Looking for event with id {} in message log", eventId);
        return repository.existsById(eventId);
    }
}
