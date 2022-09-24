package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
