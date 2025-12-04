package com.punomun.simple_wms.controller;

import com.punomun.simple_wms.dto.AddStockRequest;
import com.punomun.simple_wms.dto.StockResponse;
import com.punomun.simple_wms.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addStock(@RequestBody @Valid AddStockRequest request) {
        stockService.addStock(
                request.sku(),
                request.warehouseId(),
                request.quantity()
        );
    }

    @GetMapping
    public StockResponse getStock(
            @RequestParam String sku,
            @RequestParam Long warehouseId
    ) {
        return stockService.getStock(sku, warehouseId);
    }
}