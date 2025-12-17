package com.punomun.simple_wms.repository;

import com.punomun.simple_wms.model.Warehouse;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM Warehouse w WHERE w.id = :id")
    Optional<Warehouse> findByIdWithLock(Long id);
}
