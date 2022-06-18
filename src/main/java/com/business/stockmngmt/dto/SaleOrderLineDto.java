package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.SaleOrder;
import com.business.stockmngmt.model.SaleOrderLine;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaleOrderLineDto {

    private Integer id;

    private Article article;

    private SaleOrder saleOrder;

    /*
     * function that allow us to map from SaleOrderLine to SaleOrderLineDto
     *
     * @param SaleOrderLine saleOrderLine
     * */
    public static SaleOrderLineDto fromEntity (SaleOrderLine saleOrderLine) {
        if (saleOrderLine == null) {
            //TODO raise an exception

            return null;
        }

        return SaleOrderLineDto.builder()
                .id(saleOrderLine.getId())
                .article(saleOrderLine.getArticle())
                .saleOrder(saleOrderLine.getSaleOrder())
                .build();
    }

    /*
     * function that allow us to map from SaleOrderLineDto to SaleOrderLine
     *
     * @param SaleOrderLineDto saleOrderLineDto
     * */
    public static SaleOrderLine toEntity (SaleOrderLineDto saleOrderLineDto) {
        if (saleOrderLineDto == null) {
            //TODO raise an exception

            return null;
        }

        SaleOrderLine saleOrderLine = new SaleOrderLine();
        saleOrderLine.setId(saleOrderLineDto.getId());
        saleOrderLine.setArticle(saleOrderLineDto.getArticle());
        saleOrderLine.setSaleOrder(saleOrderLineDto.getSaleOrder());

        return saleOrderLine;
    }
}
