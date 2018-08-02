package com.ooa1769.bs.member;

public class MemberAlreadyExistException extends RuntimeException {

    public MemberAlreadyExistException(String message) {
        super(message);
    }

    public MemberAlreadyExistException(String message, Throwable ex) {
        super(message, ex);
    }
}