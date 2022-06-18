package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.SaleLine;
import com.business.stockmngmt.model.Sale;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaleLineDto {

    private Integer id;

    private Article article;

    private Sale sale;

    /*
     * function that allow us to map from SaleLine to SaleLineDto
     *
     * @param SaleLine saleLine
     * */
    public static SaleLineDto fromEntity (SaleLine saleLine) {
        if (saleLine == null) {
            //TODO raise an exception

            return null;
        }

        return SaleLineDto.builder()
                .id(saleLine.getId())
                .article(saleLine.getArticle())
                .sale(saleLine.getSale())
                .build();
    }

    /*
     * function that allow us to map from SaleLineDto to SaleLine
     *
     * @param SaleLineDto saleLineDto
     * */
    public static SaleLine toEntity (SaleLineDto saleLineDto) {
        if (saleLineDto == null) {
            //TODO raise an exception

            return null;
        }

        SaleLine saleLine = new SaleLine();
        saleLine.setId(saleLineDto.getId());
        saleLine.setArticle(saleLineDto.getArticle());
        saleLine.setSale(saleLineDto.getSale());

        return saleLine;
    }
}
