package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByCodeCategory(String codeCategory);
}
