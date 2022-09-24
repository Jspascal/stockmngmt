package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.CategoryDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.repository.CategoryRepository;
import com.business.stockmngmt.services.CategoryService;
import com.business.stockmngmt.validator.CategoryValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    // Injecting the CategoryRepository into the CategoryServiceImpl class.
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * It takes a categoryDto, validates it, and if it's valid, it saves it to the
     * database
     *
     * @param categoryDto The object that is being validated.
     * @return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
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
                        CategoryDto.toEntity(categoryDto)));
    }

    /**
     * If the id is null, log an error and return null, otherwise return the
     * categoryDto from the
     * entity or throw an exception if the entity is not found.
     *
     * @param id The ID of the category to be found
     * @return CategoryDto
     */
    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return null;
        }

        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No category with the ID " + id + " inside the database", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    /**
     * It returns a categoryDto object if the category code is not null, otherwise
     * it throws an
     * exception
     *
     * @param code String
     * @return CategoryDto
     */
    @Override
    public CategoryDto findByCode(String code) {
        if (StringUtils.hasLength(code)) {
            log.error("Category code is null");
        }

        return categoryRepository.findCategoryByCodeCategory(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No category with the ID " + code + " inside the database", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    /**
     * It takes a list of Category entities, maps them to CategoryDto objects, and
     * returns a list of
     * CategoryDto objects
     *
     * @return A list of CategoryDto objects.
     */
    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * It deletes a category by its ID
     *
     * @param id The id of the category to be deleted.
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Category ID is null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
