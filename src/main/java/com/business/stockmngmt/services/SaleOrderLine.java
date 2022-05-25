package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.SaleOrderLineDto;

import java.util.List;

public interface SaleOrderLine {

    SaleOrderLineDto save(SaleOrderLineDto saleOrderLineDto);

    SaleOrderLineDto findById(Integer id);

    List<SaleOrderLineDto> findAll();
}
