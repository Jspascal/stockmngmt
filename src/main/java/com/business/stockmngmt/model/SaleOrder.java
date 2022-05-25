package com.business.stockmngmt.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sale_orders")
public class SaleOrder extends AbstractEntity{

    @Column(name = "oderRef")
    private String orderRef;

    @Column(name = "orderRate")
    private Instant orderDate;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @OneToMany(mappedBy = "saleOrder")
    private List<SaleOrderLine> saleOrderLines;
}
