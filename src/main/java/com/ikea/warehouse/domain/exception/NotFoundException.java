package com.ikea.warehouse.domain.exception;

/**
 * Domain exception that means that the item was not found in the system
 *
 * @author robertogomez
 */
public class NotFoundException extends Exception {

    public NotFoundException(String message){
        super(message);
    }
}
