package com.business.stockmngmt.controller;

import com.business.stockmngmt.controller.api.CategoryApi;
import com.business.stockmngmt.dto.CategoryDto;
import com.business.stockmngmt.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * @param categoryDto
     * @return
     */
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }

    /**
     * @return
     */
    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}
