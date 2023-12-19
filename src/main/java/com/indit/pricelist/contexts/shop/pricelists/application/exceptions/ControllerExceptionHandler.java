package com.indit.pricelist.contexts.shop.pricelists.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDetails> handleRequestParameterException(MissingServletRequestParameterException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDetails.builder()
                        .code(exception.getStatusCode().toString())
                        .description(exception.getMessage()).build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDetails> handleIllegalArgumentException(IllegalArgumentException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDetails.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .description(exception.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDetails> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDetails.builder()
                        .code("400")
                        .description("Invalid input parameter format")
                        .build());
    }

    @ExceptionHandler(PriceListNotFoundException.class)
    public ResponseEntity<ErrorResponseDetails> handlePriceListNotFoundException(PriceListNotFoundException exception){
        return ResponseEntity
                .status(exception.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDetails.builder()
                        .code(exception.getStatusCode().toString())
                        .description(exception.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDetails> handleGeneralException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDetails.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .description(exception.getMessage()).build());
    }
}
