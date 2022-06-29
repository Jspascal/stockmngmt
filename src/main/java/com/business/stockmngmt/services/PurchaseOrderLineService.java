package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.PurchaseOrderLineDto;

import java.util.List;

public interface PurchaseOrderLineService {

    PurchaseOrderLineDto save(PurchaseOrderLineDto purchaseOrderLineDto);

    PurchaseOrderLineDto findById(Integer id);

    List<PurchaseOrderLineDto> findAll();

    void delete(Integer id);
}
