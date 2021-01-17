package com.ikea.warehouse.domain.exception;

public class NoStockException extends Exception {

    public NoStockException(String message){
        super(message);
    }
}
