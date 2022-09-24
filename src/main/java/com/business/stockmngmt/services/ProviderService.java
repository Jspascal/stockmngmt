package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.ProviderDto;

import java.util.List;

public interface ProviderService {

    ProviderDto save(ProviderDto providerDto);

    ProviderDto findById(Integer id);

    List<ProviderDto> findAll();

    void delete(Integer id);
}
