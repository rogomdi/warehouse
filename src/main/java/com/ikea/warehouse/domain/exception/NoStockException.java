package com.ikea.warehouse.domain.exception;

/**
 * Domain exception that means that there is not stock for some item
 *
 * @author robertogomez
 */
public class NoStockException extends Exception {

    public NoStockException(String message){
        super(message);
    }
}
