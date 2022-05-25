package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto articleDto);

    ClientDto findById(Integer id);

    List<ClientDto> findAll();

    void delete(Integer id);
}
