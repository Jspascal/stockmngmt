package com.business.stockmngmt.services;

import com.business.stockmngmt.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findById(Integer id);

    CategoryDto findByCodeCategory(String codeCategory);

    List<CategoryDto> findAll();

    void delete(Integer id);
}
