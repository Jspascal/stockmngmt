package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.SaleLineDto;

import java.util.List;

public interface SaleLineService {

    SaleLineDto save(SaleLineDto saleLineDto);

    SaleLineDto findById(Integer id);

    List<SaleLineDto> findAll();
}
