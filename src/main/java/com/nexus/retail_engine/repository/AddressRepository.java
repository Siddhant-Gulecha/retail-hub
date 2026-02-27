package com.nexus.retail_engine.repository;

import com.nexus.retail_engine.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}