package com.ooa1769.bs.book.domain;

public class IsbnInvalidException extends RuntimeException {

    public IsbnInvalidException(String message) {
        super(message);
    }

    public IsbnInvalidException(String message, Throwable ex) {
        super(message, ex);
    }
}

