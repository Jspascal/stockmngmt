package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto findById(Integer id);

    List<UserDto> findAll();

    void delete(Integer id);
}
