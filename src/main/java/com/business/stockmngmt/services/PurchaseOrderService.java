package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.PurchaseOrderDto;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrderDto save(PurchaseOrderDto purchaseOrderDto);

    PurchaseOrderDto findById(Integer id);

    PurchaseOrderDto findByCode(String Code);

    List<PurchaseOrderDto> findAll();

    void delete(Integer id);
}
