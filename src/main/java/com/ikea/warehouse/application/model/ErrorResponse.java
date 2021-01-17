package com.ikea.warehouse.application.model;

import lombok.Data;

/**
 * Error model for the REST endpoints
 *
 * @author robertogomez
 */
@Data
public class ErrorResponse {

    /**
     * Error code
     */
    private final int code;

    /**
     * Message with the description of the error code
     */
    private final String message;

    /**
     * Message with the cause of the error
     */
    private final String causeMessage;

}
