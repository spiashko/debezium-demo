package com.spaishko.stringcloudstreamkafka.consumer.shipment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, UUID> {
}
