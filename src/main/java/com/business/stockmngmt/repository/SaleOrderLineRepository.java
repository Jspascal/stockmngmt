package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.SaleOrder;
import com.business.stockmngmt.model.SaleOrderLine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleOrderLineRepository extends JpaRepository<SaleOrderLine, Integer> {
}
