package com.punomun.simple_wms.repository;

import com.punomun.simple_wms.model.Product;
import com.punomun.simple_wms.model.Stock;
import com.punomun.simple_wms.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductAndWarehouse(Product product, Warehouse warehouse);
}