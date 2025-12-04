package com.punomun.simple_wms.repository;

import com.punomun.simple_wms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}