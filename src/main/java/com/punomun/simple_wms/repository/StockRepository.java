package com.punomun.simple_wms.repository;

import com.punomun.simple_wms.model.Product;
import com.punomun.simple_wms.model.Stock;
import com.punomun.simple_wms.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductAndWarehouse(Product product, Warehouse warehouse);

    @Query("SELECT COALESCE(SUM(s.quantity), 0) FROM Stock s WHERE s.warehouse.id = :warehouseId")
    Integer getCurrentStockInWarehouse(Long warehouseId);
}