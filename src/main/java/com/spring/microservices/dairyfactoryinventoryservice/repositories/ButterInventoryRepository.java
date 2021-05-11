package com.spring.microservices.dairyfactoryinventoryservice.repositories;

import com.spring.microservices.dairyfactoryinventoryservice.domain.ButterInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ButterInventoryRepository extends JpaRepository<ButterInventory, UUID> {

    List<ButterInventory> findAllByButterId(UUID butterId);
}
