package com.example.exceptionhandlingwebflux.customexception.exception;

import com.example.exceptionhandlingwebflux.customexception.model.CustomErrorResponse;
import lombok.Getter;

public class CustomErrorException extends RuntimeException {
    @Getter
    private CustomErrorResponse errorResponse;

    public CustomErrorException(String message, CustomErrorResponse errorResponse) {
        super(message);
        this.errorResponse = errorResponse;
    }
}
