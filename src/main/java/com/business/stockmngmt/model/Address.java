package com.business.stockmngmt.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Address {

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "town")
    private String town;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "country")
    private String country;
}
