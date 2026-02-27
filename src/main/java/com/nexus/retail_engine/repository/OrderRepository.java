package com.nexus.retail_engine.repository;

import com.nexus.retail_engine.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}