package com.spaishko.debeziumdemo.consumer.messagelog;

import java.util.UUID;

public interface MessageLogService {

    void processed(UUID eventId);

    boolean alreadyProcessed(UUID eventId);

}
