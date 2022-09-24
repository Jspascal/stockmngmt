package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Integer id);

    List<RoleDto> findAll();

    void delete(Integer id);
}
