package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.StockMvt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMvtRepository extends JpaRepository<StockMvt, Integer> {
}
