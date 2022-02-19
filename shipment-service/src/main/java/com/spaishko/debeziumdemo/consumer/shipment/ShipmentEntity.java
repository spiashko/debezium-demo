/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package com.spaishko.debeziumdemo.consumer.shipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class ShipmentEntity {

    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "customer_uuid")
    private UUID customerUuid;

    @Column(name = "order_uuid")
    private UUID orderUuid;

    @Column(name = "order_date")
    private OffsetDateTime orderDate;

}
