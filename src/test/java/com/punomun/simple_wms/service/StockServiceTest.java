package com.punomun.simple_wms.service;

import com.punomun.simple_wms.exception.WarehouseFullException;
import com.punomun.simple_wms.model.Product;
import com.punomun.simple_wms.model.Warehouse;
import com.punomun.simple_wms.repository.ProductRepository;
import com.punomun.simple_wms.repository.StockRepository;
import com.punomun.simple_wms.repository.WarehouseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock private ProductRepository productRepository;
    @Mock private WarehouseRepository warehouseRepository;
    @Mock private StockRepository stockRepository;

    @InjectMocks private StockService stockService;

    @Test
    @DisplayName("Should throw WarehouseFullException when capacity is exceeded")
    void shouldThrowExceptionWhenWarehouseIsFull() {
        String sku = "ITEM-001";
        Long warehouseId = 1L;
        Integer quantityToAdd = 10;
        Integer capacity = 100;
        Integer currentStock = 95;

        Product product = new Product();
        product.setSku(sku);

        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseId);
        warehouse.setCapacity(capacity);

        when(productRepository.findBySku(sku)).thenReturn(Optional.of(product));
        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.of(warehouse));
        when(stockRepository.getCurrentStockInWarehouse(warehouseId)).thenReturn(currentStock);

        assertThrows(WarehouseFullException.class, () -> stockService.addStock(sku, warehouseId, quantityToAdd));

        verify(stockRepository, never()).save(any());
    }
}