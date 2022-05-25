package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Client;
import com.business.stockmngmt.model.SaleOrder;
import com.business.stockmngmt.model.SaleOrderLine;
import lombok.Builder;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class SaleOrderDto {

    private Integer id;

    private String orderRef;

    private Instant orderDate;

    private Client client;

    @JsonIgnore
    private List<SaleOrderLineDto> saleOrderLines;

    /*
     * function to map from SaleOrder to SaleOrderDto
     *
     * @param SaleOrder saleOrder
     * */
    public static SaleOrderDto fromEntity (SaleOrder saleOrder) {
        if (saleOrder == null) {
            //TODO raise an exception

            return null;
        }

        return SaleOrderDto.builder()
                .id(saleOrder.getId())
                .orderRef(saleOrder.getOrderRef())
                .orderDate(saleOrder.getOrderDate())
                .client(saleOrder.getClient())
                .build();
    }

    /*
     * function to map from SaleOrderDto to SaleOrder
     *
     * @param SaleOrderDto saleOrderDto
     * */
    public static SaleOrder toEntity (SaleOrderDto saleOrderDto) {
        if (saleOrderDto == null) {
            //TODO raise an exception

            return null;
        }

        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setId(saleOrderDto.getId());
        saleOrder.setOrderRef(saleOrderDto.getOrderRef());
        saleOrder.setOrderDate(saleOrderDto.getOrderDate());
        saleOrder.setClient(saleOrderDto.getClient());

        return saleOrder;
    }
}
