package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
