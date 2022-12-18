package com.yunus.stockapi.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
