package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.PurchaseOrderLine;
import com.business.stockmngmt.model.PurchaseOrder;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PurchaseOrderLineDto {

    private Integer id;

    private Article article;

    private Integer idEntreprise;

    private PurchaseOrder purchaseOrder;

    /*
     * function that allow us to map from PurchaseOrderLine to PurchaseOrderLineDto
     *
     * @param PurchaseOrderLine purchaseOrderLine
     * */
    public static PurchaseOrderLineDto fromEntity (PurchaseOrderLine purchaseOrderLine) {
        if (purchaseOrderLine == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.PURCHASE_ORDER_LINE_NOT_FOUND);

        }

        return PurchaseOrderLineDto.builder()
                .id(purchaseOrderLine.getId())
                .article(purchaseOrderLine.getArticle())
                .idEntreprise(purchaseOrderLine.getIdEntreprise())
                .purchaseOrder(purchaseOrderLine.getPurchaseOrder())
                .build();
    }

    /*
     * function that allow us to map from PurchaseOrderLineDto to PurchaseOrderLine
     *
     * @param PurchaseOrderLineDto purchaseOrderLineDto
     * */
    public static PurchaseOrderLine toEntity (PurchaseOrderLineDto purchaseOrderLineDto) {
        if (purchaseOrderLineDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.PURCHASE_ORDER_LINE_NOT_FOUND);

        }

        PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();
        purchaseOrderLine.setId(purchaseOrderLineDto.getId());
        purchaseOrderLine.setArticle(purchaseOrderLineDto.getArticle());
        purchaseOrderLine.setIdEntreprise(purchaseOrderLineDto.getIdEntreprise());
        purchaseOrderLine.setPurchaseOrder(purchaseOrderLineDto.getPurchaseOrder());

        return purchaseOrderLine;
    }
}
