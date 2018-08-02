package com.ooa1769.bs.book.domain;

import lombok.Getter;

@Getter
public class Price {

    private Integer price;
    private Integer salePrice;

    public Price(Integer price, Integer salePrice) {
        if (price < 0 || salePrice < 0) {
            throw new IllegalArgumentException("Price 값은 0이상이어야 합니다.");
        }
        this.price = price;
        this.salePrice = salePrice;
    }

    public int discountRate() {
        return (price - salePrice) * 100 / price;
    }
}
