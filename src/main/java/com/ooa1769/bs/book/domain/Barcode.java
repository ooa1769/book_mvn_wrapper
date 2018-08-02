package com.ooa1769.bs.book.domain;

import lombok.Getter;

@Getter
public class Barcode {

    private String barcode;
    private String ebookBarcode;

    public Barcode(String barcode, String ebookBarcode) {
        this.barcode = barcode;
        this.ebookBarcode = ebookBarcode;
    }
}
