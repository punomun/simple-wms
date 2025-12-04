package com.punomun.simple_wms.exception;

public class WarehouseFullException extends RuntimeException {
    public WarehouseFullException(String message) {
        super(message);
    }
}
