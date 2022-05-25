package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine, Integer> {
}
