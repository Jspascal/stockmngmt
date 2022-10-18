package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.SaleOrderDto;

import java.util.List;

public interface SaleOrderService {

    SaleOrderDto save(SaleOrderDto saleOrderDto);

    SaleOrderDto findById(Integer id);

    SaleOrderDto findByRef(String ref);

    List<SaleOrderDto> findAll();

    void delete(Integer id);
}
