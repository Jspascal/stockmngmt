package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {
}
