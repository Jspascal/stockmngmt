package com.business.stockmngmt.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "providers")
public class Provider extends AbstractEntity{

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Embedded
    private Address address;

    @Column(name = "photoProvider")
    private String photoProvider;

    @Column(name = "emailProvider")
    private String emailProvider;

    @Column(name = "numTelProvider")
    private String numProvider;

    @OneToMany(mappedBy = "provider")
    private List<PurchaseOrder> purchaseOrders;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;
}
