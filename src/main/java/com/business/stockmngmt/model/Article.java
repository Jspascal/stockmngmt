package com.business.stockmngmt.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "articles")
public class Article extends AbstractEntity{

    @Column(name = "codeArticle")
    private String codeArticle;

    @Column(name = "labelArticle")
    private String labelArticle;

    @Column(name = "netPrice")
    private BigDecimal netprice;

    @Column(name = "vatRate")
    BigDecimal vatRate;

    @Column(name = "taxedPrice")
    private BigDecimal taxedPrice;

    @Column(name = "photoArticle")
    String photoArticle;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;

}
