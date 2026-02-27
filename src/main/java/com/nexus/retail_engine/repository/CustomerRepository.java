package com.nexus.retail_engine.repository;

import com.nexus.retail_engine.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}