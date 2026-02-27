package com.nexus.retail_engine.repository;

import com.nexus.retail_engine.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}