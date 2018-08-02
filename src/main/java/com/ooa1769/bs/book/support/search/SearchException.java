package com.ooa1769.bs.book.support.search;

public class SearchException extends RuntimeException {

    public SearchException(String message) {
        super(message);
    }

    public SearchException(String message, Throwable ex) {
        super(message, ex);
    }
}
