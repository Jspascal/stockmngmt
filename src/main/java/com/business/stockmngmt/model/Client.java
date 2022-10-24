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
@Table(name = "clients")
public class Client extends AbstractEntity{

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Embedded
    private Address address;

    @Column(name = "photoClient")
    private String photoClient;

    @Column(name = "emailClient")
    private String emailClient;

    @Column(name = "numTelClient")
    private String numTelClient;

    @OneToMany(mappedBy = "client")
    private List<SaleOrder> saleOrders;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

}
