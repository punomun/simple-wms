package com.punomun.simple_wms.config;

import com.punomun.simple_wms.model.Product;
import com.punomun.simple_wms.model.Warehouse;
import com.punomun.simple_wms.repository.ProductRepository;
import com.punomun.simple_wms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.findBySku("ITEM-001").isEmpty()) {
            Product shirt = new Product();
            shirt.setSku("ITEM-001");
            shirt.setName("Red T-Shirt XL");
            shirt.setPrice(new BigDecimal("19.99"));
            productRepository.save(shirt);
            log.info("Seeded Product: {}", shirt.getSku());
        }

        if (warehouseRepository.count() == 0) {
            Warehouse arteixo = new Warehouse();
            arteixo.setName("Arteixo");
            arteixo.setLocation("Calle Falsa, 123");
            arteixo.setCapacity(1000);
            warehouseRepository.save(arteixo);
            log.info("Seeded Warehouse: {}", arteixo.getName());
        }
    }
}