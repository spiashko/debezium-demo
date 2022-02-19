package com.spaishko.stringcloudstreamkafka.consumer.stream.handlers.order;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@NoArgsConstructor
@Getter
@Setter
class OrderModel {

    private UUID uuid;
    private UUID customerUuid;
    private OffsetDateTime orderDate;

}
