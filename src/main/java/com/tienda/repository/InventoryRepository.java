package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	Inventory findByProductId(Long productId);
}
