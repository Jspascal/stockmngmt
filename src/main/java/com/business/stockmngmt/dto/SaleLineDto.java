package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.SaleLine;
import com.business.stockmngmt.model.Sale;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaleLineDto {

    private Integer id;

    private Integer idEntreprise;

    private Article article;

    private Sale sale;

    /*
     * function that allow us to map from SaleLine to SaleLineDto
     *
     * @param SaleLine saleLine
     * */
    public static SaleLineDto fromEntity (SaleLine saleLine) {
        if (saleLine == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.SALE_LINE_NOT_FOUND);

        }

        return SaleLineDto.builder()
                .id(saleLine.getId())
                .idEntreprise(saleLine.getIdEntreprise())
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
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.SALE_LINE_NOT_FOUND);

        }

        SaleLine saleLine = new SaleLine();
        saleLine.setId(saleLineDto.getId());
        saleLine.setIdEntreprise(saleLineDto.getIdEntreprise());
        saleLine.setArticle(saleLineDto.getArticle());
        saleLine.setSale(saleLineDto.getSale());

        return saleLine;
    }
}
