package com.spaishko.debeziumdemo.producer.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


@FieldNameConstants
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "purchase_order")
public class OrderEntity {

    @JsonView({OrderView.Retrieve.class})
    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    @JsonView({OrderView.Retrieve.class, OrderView.Create.class})
    @NotNull(groups = OrderValidation.New.class)
    @Column(name = "customer_uuid")
    private UUID customerUuid;

    @JsonView({OrderView.Retrieve.class, OrderView.Create.class})
    @NotNull(groups = OrderValidation.New.class)
    @PastOrPresent
    @Column(name = "order_date")
    private OffsetDateTime orderDate;

    @JsonIgnoreProperties(OrderLineEntity.Fields.order)
    @JsonView({OrderView.Retrieve.class, OrderView.Create.class})
    @Valid
    @NotEmpty(groups = OrderValidation.New.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = OrderLineEntity.Fields.order)
    private List<OrderLineEntity> lineItems;

    public void setLineItems(List<OrderLineEntity> lineItems) {
        if (lineItems != null) {
            lineItems.forEach(item -> item.setOrder(this));
            this.lineItems = lineItems;
        }
    }
}
