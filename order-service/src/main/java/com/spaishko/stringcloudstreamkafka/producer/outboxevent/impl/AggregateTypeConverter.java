package com.spaishko.stringcloudstreamkafka.producer.outboxevent.impl;

import com.spaishko.stringcloudstreamkafka.producer.domainevent.AggregateType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

@Converter
public class AggregateTypeConverter implements AttributeConverter<AggregateType, String> {

    private static final Map<AggregateType, String> mapping;

    static {
        mapping = new HashMap<>();
        mapping.put(AggregateType.ORDER, "order");
    }

    @Override
    public String convertToDatabaseColumn(AggregateType type) {
        return mapping.get(type);
    }

    @Override
    public AggregateType convertToEntityAttribute(String dbValue) {
        return mapping.entrySet().stream()
                .filter(e -> dbValue.equals(e.getValue()))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalArgumentException(
                        "failed to convert " + dbValue + " to enum"));
    }
}