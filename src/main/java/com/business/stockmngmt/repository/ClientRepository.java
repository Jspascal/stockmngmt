package com.business.stockmngmt.repository;

import com.business.stockmngmt.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
