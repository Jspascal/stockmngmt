package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.SaleDto;

import java.util.List;

public interface SaleService {

    SaleDto save(SaleDto saleDto);

    SaleDto findById(Integer id);

    SaleDto findByCode(String code);

    List<SaleDto> findAll();

    void delete(Integer id);
}
