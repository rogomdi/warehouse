package com.ikea.warehouse.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum class to handle all the errors in the application layer
 *
 * @author robertogomez
 */
@Getter
@AllArgsConstructor
public enum ApplicationError {

    INVALID_OPERATION(1001, "Invalid operation"),
    INVALID_OPERATION_NOT_FOUND(1002, "Item not found"),
    INVALID_OPERATION_INCORRECT_FILE_TYPE(1003, "File is not correct"),
    INTERNAL_ERROR(5001, "Internal error");

    /**
     * Error code
     */
    private final int code;

    /**
     * Message with the description of the error code
     */
    private final String message;

}
