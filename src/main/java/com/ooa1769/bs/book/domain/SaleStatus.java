package com.ooa1769.bs.book.domain;

public enum SaleStatus {

    Y("정상판매"),
    N("가격확인중");

    private String status;

    SaleStatus(String status) {
        this.status = status;
    }

    public boolean isSale() {
        return this == Y ? true : false;
    }

    @Override
    public String toString() {
        return status;
    }
}
