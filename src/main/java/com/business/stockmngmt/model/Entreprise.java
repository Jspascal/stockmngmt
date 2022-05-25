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
@Table(name = "entreprises")
public class Entreprise extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private Address address;

    @Column(name = "logoEnt")
    private String logoEnt;

    @Column(name = "emailEnt")
    private String emailEnt;

    @Column(name = "numTelEnt")
    private String numTelEnt;

    @Column(name = "website")
    private String website;

    @OneToMany(mappedBy = "entreprise")
    private List<User> users;
}
