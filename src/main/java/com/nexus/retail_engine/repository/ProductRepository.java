package com.nexus.retail_engine.repository;

import com.nexus.retail_engine.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}