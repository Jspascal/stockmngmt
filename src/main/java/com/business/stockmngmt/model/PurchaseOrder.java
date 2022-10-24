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
@Table(name = "purchase_order")
public class PurchaseOrder extends AbstractEntity{

    @Column(name = "codeOrder")
    private String codeOrder;

    @Column(name = "orderDate")
    private Instant orderDate;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idProvider")
    private Provider provider;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderLine> purchaseOrderLines;

}
