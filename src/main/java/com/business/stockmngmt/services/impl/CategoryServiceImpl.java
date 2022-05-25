package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.CategoryDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Category;
import com.business.stockmngmt.repository.CategoryRepository;
import com.business.stockmngmt.services.CategoryService;
import com.business.stockmngmt.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    /**
     * @param categoryDto
     * @return CategoryDto
     */
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", categoryDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }

        return CategoryDto.fromEntity(
                categoryRepository.save(
                        CategoryDto.toEntity(categoryDto)
                )
        );
    }

    /**
     * @param id
     * @return CategoryDto
     */
    @Override
    public CategoryDto findById(Integer id) {
        if(id == null){
            log.error("Article ID is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No category with the ID " + id + " inside the database", ErrorCodes.CATEGORY_NOT_FOUND
                )
        );
    }

    /**
     * @return List<CategoryDto>
     */
    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
            .map(CategoryDto::fro);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {

    }
}
