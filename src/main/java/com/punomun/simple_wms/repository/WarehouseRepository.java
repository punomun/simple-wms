package com.punomun.simple_wms.repository;

import com.punomun.simple_wms.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
