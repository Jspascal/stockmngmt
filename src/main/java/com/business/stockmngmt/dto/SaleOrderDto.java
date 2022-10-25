package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Client;
import com.business.stockmngmt.model.SaleOrder;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class SaleOrderDto {

    private Integer id;

    private String orderRef;

    private Instant orderDate;

    private Integer idEntreprise;

    private Client client;

    private List<SaleOrderLineDto> saleOrderLines;

    /*
     * function that allow us to map from SaleOrder to SaleOrderDto
     *
     * @param SaleOrder saleOrder
     * */
    public static SaleOrderDto fromEntity (SaleOrder saleOrder) {
        if (saleOrder == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.SALE_ORDER_NOT_FOUND);

        }

        return SaleOrderDto.builder()
                .id(saleOrder.getId())
                .orderRef(saleOrder.getOrderRef())
                .orderDate(saleOrder.getOrderDate())
                .idEntreprise(saleOrder.getIdEntreprise())
                .client(saleOrder.getClient())
                .build();
    }

    /*
     * function that allow us to map from SaleOrderDto to SaleOrder
     *
     * @param SaleOrderDto saleOrderDto
     * */
    public static SaleOrder toEntity (SaleOrderDto saleOrderDto) {
        if (saleOrderDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.SALE_ORDER_NOT_FOUND);

        }

        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setId(saleOrderDto.getId());
        saleOrder.setOrderRef(saleOrderDto.getOrderRef());
        saleOrder.setOrderDate(saleOrderDto.getOrderDate());
        saleOrder.setIdEntreprise(saleOrderDto.getIdEntreprise());
        saleOrder.setClient(saleOrderDto.getClient());

        return saleOrder;
    }
}
