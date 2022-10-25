package com.business.stockmngmt.repository;

import com.business.stockmngmt.dto.SaleDto;
import com.business.stockmngmt.model.Sale;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Optional<Sale> findSaleByCode(String code);
}
