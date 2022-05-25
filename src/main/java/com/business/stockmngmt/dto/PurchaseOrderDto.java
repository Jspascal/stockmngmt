package com.business.stockmngmt.dto;

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

    private List<PurchaseOrderDto> purchaseOrders;

    public static PurchaseOrderDto fromEntity (PurchaseOrder purchaseOrder) {
        if (purchaseOrder == null) {
            //TODO raise an exception

            return null;
        }

        return PurchaseOrderDto.builder()
                .id(purchaseOrder.getId())
                .codeOrder(purchaseOrder.getCodeOrder())
                .orderDate(purchaseOrder.getOrderDate())
                .provider(purchaseOrder.getProvider())
                .build();
    }

    /*
     * function to map from PurchaseOrderDto to PurchaseOrder
     *
     * @param PurchaseOrderDto purchaseOrderDto
     * */
    public static PurchaseOrder toEntity (PurchaseOrderDto purchaseOrderDto) {
        if (purchaseOrderDto == null) {
            //TODO raise an exception

            return null;
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(purchaseOrderDto.getId());
        purchaseOrder.setCodeOrder(purchaseOrderDto.getCodeOrder());
        purchaseOrder.setOrderDate(purchaseOrderDto.getOrderDate());
        purchaseOrder.setProvider(purchaseOrderDto.getProvider());

        return purchaseOrder;
    }
}
