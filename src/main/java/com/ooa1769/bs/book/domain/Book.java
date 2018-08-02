package com.ooa1769.bs.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class Book {

    private String title;
    private String contents;
    private String url;
    private Isbn isbn;
    private ZonedDateTime datetime;
    private List<String> authors;
    private String publisher;
    private List<String> translators;
    private Price price;
    private String category;
    private String thumbnail;
    private Barcode barcode;
    private SaleStatus saleStatus;

    @Builder
    public Book(String title, String contents, String url, Isbn isbn, ZonedDateTime datetime, List<String> authors, String publisher, List<String> translators, Price price, String category, String thumbnail, Barcode barcode, SaleStatus saleStatus) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.isbn = isbn;
        this.datetime = datetime;
        this.authors = authors;
        this.publisher = publisher;
        this.translators = translators;
        this.price = price;
        this.category = category;
        this.thumbnail = thumbnail;
        this.barcode = barcode;
        this.saleStatus = saleStatus;
    }

    public String getAuthors() {
        return authors.stream().collect(Collectors.joining(","));
    }

    public String getIsbn() {
        return isbn.getIsbn();
    }

    public Integer getPrice() {
        return price.getPrice();
    }

    public Integer getSalePrice() {
        return price.getSalePrice();
    }

    public boolean isNullDateTime() {
        return datetime == null;
    }
}
