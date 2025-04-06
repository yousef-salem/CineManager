package com.youssef.CineManager.exception;

import lombok.Getter;

@Getter
public class NotFoundResourceException extends RuntimeException {
    private final String errorMessage;

    public NotFoundResourceException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

}