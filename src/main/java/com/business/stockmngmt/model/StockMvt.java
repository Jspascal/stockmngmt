package com.business.stockmngmt.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stock_mvts")
public class StockMvt extends AbstractEntity{

    @Column(name = "mvtDate")
    private Instant mvtDate;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "mvtType")
    private MvtStockType mvtType;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
