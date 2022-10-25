package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Sale;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Builder
@Data
public class SaleDto {

    private Integer id;

    private String saleCode;

    private Instant saleDate;

    private String comments;

    private Integer idEntreprise;

    /*
     * function that allow us to map from Sale to SaleDto
     *
     * @param Sale sale
     * */
    public static SaleDto fromEntity (Sale sale) {
        if (sale == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.SALE_NOT_FOUND);

        }

        return SaleDto.builder()
                .id(sale.getId())
                .saleCode(sale.getSaleCode())
                .saleDate(sale.getSaleDate())
                .comments(sale.getComments())
                .idEntreprise(sale.getIdEntreprise())
                .build();
    }

    /*
     * function that allow us to map from SaleDto to Sale
     *
     * @param SaleDto saleDto
     * */
    public static Sale toEntity (SaleDto saleDto) {
        if (saleDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.SALE_NOT_FOUND);

        }

        Sale sale = new Sale();
        sale.setId(saleDto.getId());
        sale.setSaleCode(saleDto.getSaleCode());
        sale.setSaleDate(saleDto.getSaleDate());
        sale.setComments(saleDto.getComments());
        sale.setIdEntreprise(saleDto.getIdEntreprise());

        return sale;
    }
}
