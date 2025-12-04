package com.punomun.simple_wms.dto;

public record StockResponse(
        String sku,
        Long warehouseId,
        Integer quantity
) {}