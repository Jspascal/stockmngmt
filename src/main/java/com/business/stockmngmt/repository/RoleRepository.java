package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
