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
@Table(name = "users")
public class User extends AbstractEntity{

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Embedded
    private Address address;

    @Column(name = "photoUser")
    private String photoUser;

    @Column(name = "emailUser")
    private String emailUser;

    @Column(name = "password")
    private String password;

    @Column(name = "numTelUser")
    private String numTelUser;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

    @ManyToOne()
    @JoinColumn(name = "idRole")
    private Role role;
}
