package com.spaishko.debeziumdemo.producer.order;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@FieldNameConstants
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_line")
public class OrderLineEntity {

    @JsonView({OrderView.Retrieve.class})
    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;

    @JsonView({OrderView.Retrieve.class, OrderView.Create.class})
    @NotEmpty(groups = OrderValidation.New.class)
    @Column(name = "item")
    private String item;

    @JsonView({OrderView.Retrieve.class, OrderView.Create.class})
    @NotNull(groups = OrderValidation.New.class)
    @Min(value = 1, groups = OrderValidation.New.class)
    @Column(name = "quantity")
    private Integer quantity;

    @JsonView({OrderView.Retrieve.class, OrderView.Create.class})
    @NotNull(groups = OrderValidation.New.class)
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @JsonView({OrderView.Retrieve.class, OrderView.Create.class})
    @NotNull(groups = OrderValidation.New.class)
    @ManyToOne
    @JoinColumn(name = "fk_order")
    private OrderEntity order;

    @JsonView({OrderView.Retrieve.class})
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_line_status")
    private OrderLineStatus orderLineStatus;

}
