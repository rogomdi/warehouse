package com.ikea.warehouse.application.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseException extends RuntimeException{

    /**
     * Attribute to store the error code defined on {@link ApplicationError} enum
     */
    private final int errorCode;

    public WarehouseException(ApplicationError applicationError, String description) {
        super(applicationError.getMessage());
        this.errorCode = applicationError.getCode();
    }

    public WarehouseException(ApplicationError applicationError, Throwable cause) {
        super(applicationError.getMessage(), cause);
        this.errorCode = applicationError.getCode();
    }
}
