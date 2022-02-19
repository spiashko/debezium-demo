package com.spaishko.debeziumdemo.producer.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spaishko.debeziumdemo.producer.order.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderResource {

    private final OrderManagementService orderManagementService;

    @JsonView(OrderView.Retrieve.class)
    @PostMapping
    public OrderEntity addOrder(
            @RequestBody
            @Validated(OrderValidation.New.class)
            @JsonView(OrderView.Create.class)
                    OrderEntity entityToCreate) {
        return orderManagementService.addOrder(entityToCreate);
    }

    @JsonView(OrderView.Retrieve.class)
    @PutMapping("/{orderId}/lines/{orderLineId}")
    public OrderEntity updateOrderLine(
            @PathVariable("orderId") UUID orderId,
            @PathVariable("orderLineId") UUID orderLineId,
            @RequestBody @Valid OrderLineUpdateStatusRequest request) {
        return orderManagementService.updateOrderLineStatus(orderId, orderLineId, request.getNewStatus());
    }


    @ToString
    @Getter
    @Setter
    private static class OrderLineUpdateStatusRequest {

        @NotNull
        private OrderLineStatus newStatus;

    }
}
