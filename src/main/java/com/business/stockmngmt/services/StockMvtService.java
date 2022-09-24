package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.StockMvtDto;

import java.util.List;

public interface StockMvtService {

    StockMvtDto save(StockMvtDto stockMvtDto);

    StockMvtDto findById(Integer id);

    List<StockMvtDto> findAll();

    void delete(Integer id);
}
