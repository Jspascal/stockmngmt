package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
