package com.ikea.warehouse.application.exception;

import com.ikea.warehouse.application.model.rest.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handlers in the application handler for the REST endpoints
 *
 * @author robertogomez
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * Handler for all the {@link WarehouseException} and its children
     *
     * @param ex Exception to handle
     * @return Response entity with the {@link ErrorResponse} on its body
     */
    @ExceptionHandler(WarehouseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConversion(WarehouseException ex) {
        if (ex.getErrorCode() == ApplicationError.INVALID_OPERATION_NOT_FOUND.getCode())
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), ex.getMessage(), ex.getCause().getMessage()), HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), ex.getMessage(), ex.getCause().getMessage()), HttpStatus.BAD_REQUEST);
    }

}
