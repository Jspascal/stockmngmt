package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.MvtStockType;
import com.business.stockmngmt.model.StockMvt;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class StockMvtDto {

    private Integer id;

    private Instant mvtDate;

    private BigDecimal quantity;

    private MvtStockType mvtType;

    private Article article;

    /*
     * function to map from StockMvt to StockMvtDto
     *
     * @param StockMvt stockMvt
     * */
    public static StockMvtDto fromEntity (StockMvt stockMvt) {
        if (stockMvt == null) {
            //TODO raise an exception

            return null;
        }

        return StockMvtDto.builder()
                .id(stockMvt.getId())
                .mvtDate(stockMvt.getMvtDate())
                .mvtType(stockMvt.getMvtType())
                .quantity(stockMvt.getQuantity())
                .article(stockMvt.getArticle())
                .build();
    }

    /*
     * function to map from StockMvtDto to StockMvt
     *
     * @param StockMvtDto stockMvtDto
     * */
    public static StockMvt toEntity (StockMvtDto stockMvtDto) {
        if (stockMvtDto == null) {
            //TODO raise an exception

            return null;
        }

        StockMvt stockMvt = new StockMvt();
        stockMvt.setId(stockMvtDto.getId());
        stockMvt.setMvtDate(stockMvtDto.getMvtDate());
        stockMvt.setMvtType(stockMvtDto.getMvtType());
        stockMvt.setQuantity(stockMvtDto.getQuantity());
        stockMvt.setArticle(stockMvtDto.getArticle());

        return stockMvt;
    }
}
