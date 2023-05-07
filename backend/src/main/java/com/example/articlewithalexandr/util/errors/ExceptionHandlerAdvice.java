package com.example.articlewithalexandr.util.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Vlad Utts
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MyException.class)
    private ResponseEntity<ErrorResponse> handleException(MyException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
