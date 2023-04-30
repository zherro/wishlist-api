package com.api.wishlist.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Getter @AllArgsConstructor
    public static class ErrorResponse {
        private Integer code;
        private String message;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleGenericException(BusinessException ex, WebRequest request) {
        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return this.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleUncaught(NotFoundException ex, WebRequest request) {

        var errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(), ex.getMessage());
        return this.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler(BlockAccessException.class)
    public ResponseEntity<Object> handleBlockAccessException(BlockAccessException ex, WebRequest request) {

        var errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return this.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

            if(body == null) {
                body = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            }

            return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}