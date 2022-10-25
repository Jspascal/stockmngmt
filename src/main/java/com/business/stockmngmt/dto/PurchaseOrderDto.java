package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Provider;
import com.business.stockmngmt.model.PurchaseOrder;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class PurchaseOrderDto {

    private Integer id;

    private String codeOrder;

    private Instant orderDate;

    private Provider provider;

    private Integer idEntreprise;

    private List<PurchaseOrderDto> purchaseOrders;

    public static PurchaseOrderDto fromEntity (PurchaseOrder purchaseOrder) {
        if (purchaseOrder == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.PURCHASE_ORDER_NOT_FOUND);

        }

        return PurchaseOrderDto.builder()
                .id(purchaseOrder.getId())
                .codeOrder(purchaseOrder.getCodeOrder())
                .orderDate(purchaseOrder.getOrderDate())
                .provider(purchaseOrder.getProvider())
                .idEntreprise(purchaseOrder.getIdEntreprise())
                .build();
    }

    /*
     * function that allow us to map from PurchaseOrderDto to PurchaseOrder
     *
     * @param PurchaseOrderDto purchaseOrderDto
     * */
    public static PurchaseOrder toEntity (PurchaseOrderDto purchaseOrderDto) {
        if (purchaseOrderDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.PURCHASE_ORDER_NOT_FOUND);

        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(purchaseOrderDto.getId());
        purchaseOrder.setCodeOrder(purchaseOrderDto.getCodeOrder());
        purchaseOrder.setOrderDate(purchaseOrderDto.getOrderDate());
        purchaseOrder.setProvider(purchaseOrderDto.getProvider());
        purchaseOrder.setIdEntreprise(purchaseOrderDto.getIdEntreprise());

        return purchaseOrder;
    }
}
