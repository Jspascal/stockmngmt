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
@Table(name = "sales")
public class Sale extends AbstractEntity{

    @Column(name = "saleCode")
    private String saleCode;

    @Column(name = "saleDate")
    private Instant saleDate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;
}
