package com.ooa1769.bs.book.domain;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
