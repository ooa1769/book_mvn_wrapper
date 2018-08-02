package com.ooa1769.bs.book.domain;

public class BookMarkNotFoundException extends RuntimeException {

    public BookMarkNotFoundException(String message) {
        super(message);
    }

    public BookMarkNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}

