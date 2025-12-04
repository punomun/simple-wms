package com.punomun.simple_wms.service;

import com.punomun.simple_wms.dto.StockResponse;
import com.punomun.simple_wms.exception.ResourceNotFoundException;
import com.punomun.simple_wms.exception.WarehouseFullException;
import com.punomun.simple_wms.model.Product;
import com.punomun.simple_wms.model.Stock;
import com.punomun.simple_wms.model.Warehouse;
import com.punomun.simple_wms.repository.ProductRepository;
import com.punomun.simple_wms.repository.StockRepository;
import com.punomun.simple_wms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final StockRepository stockRepository;

    @Transactional
    public void addStock(String sku, Long warehouseId, Integer quantity) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with SKU: " + sku));

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + warehouseId));

        Integer currentStockLevel = stockRepository.getCurrentStockInWarehouse(warehouseId);
        if (currentStockLevel + quantity > warehouse.getCapacity()) throw new WarehouseFullException(
                String.format("Warehouse %s is full: Capacity: %d, Current: %d, Attempted: %d",
                        warehouse.getName(),
                        warehouse.getCapacity(),
                        currentStockLevel,
                        quantity)
        );

        Stock stock = stockRepository.findByProductAndWarehouse(product, warehouse)
                .orElse(new Stock());

        if (stock.getId() == null) {
            stock.setProduct(product);
            stock.setWarehouse(warehouse);
            stock.setQuantity(0);
        }

        stock.setQuantity(stock.getQuantity() + quantity);

        stockRepository.save(stock);
    }

    public StockResponse getStock(String sku, Long warehouseId) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with SKU: " + sku));

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + warehouseId));

        Integer quantity = stockRepository.findByProductAndWarehouse(product, warehouse)
                .map(Stock::getQuantity)
                .orElse(0);

        return new StockResponse(sku, warehouseId, quantity);
    }
}