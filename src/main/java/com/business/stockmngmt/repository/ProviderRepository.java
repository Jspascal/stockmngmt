package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
}
