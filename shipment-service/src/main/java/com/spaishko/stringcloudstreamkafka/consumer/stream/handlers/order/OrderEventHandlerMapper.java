package com.spaishko.stringcloudstreamkafka.consumer.stream.handlers.order;

import com.spaishko.stringcloudstreamkafka.consumer.shipment.ShipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
interface OrderEventHandlerMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "orderUuid", source = "uuid")
    ShipmentEntity map(OrderModel o);

}
