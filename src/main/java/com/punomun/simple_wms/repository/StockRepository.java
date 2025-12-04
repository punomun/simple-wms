package com.punomun.simple_wms.repository;

import com.punomun.simple_wms.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}