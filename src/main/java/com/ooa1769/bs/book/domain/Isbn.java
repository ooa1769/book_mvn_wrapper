package com.ooa1769.bs.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class Isbn {

    private final static int ISBN_8_LENGTH = 8;
    private final static int ISBN_10_LENGTH = 10;
    private final static int ISBN_12_LENGTH = 12;
    private final static int ISBN_13_LENGTH = 13;

    public final static String NOT_EXISTS_ISBN_STR = "NOT_EXISTS_ISBN";
    public final static Isbn NOT_EXISTS_ISBN = new Isbn(NOT_EXISTS_ISBN_STR);

    @Getter
    private String isbn;

    public Isbn(String isbn) {
        if (!isValidIsbnLength(isbn)) {
            throw new IsbnInvalidException("유효하지 않는 ISBN입니다.");
        }
        this.isbn = isbn;
    }

    // 네이버 api검색시 8자리 12자리인 케이스
    private boolean isValidIsbnLength(String isbn) {
        return isbn.length() == ISBN_13_LENGTH ||
                isbn.length() == ISBN_12_LENGTH ||
                isbn.length() == ISBN_10_LENGTH ||
                isbn.length() == ISBN_8_LENGTH ||
                NOT_EXISTS_ISBN_STR.equals(isbn);
    }

    public static Isbn createIsbnByApi(String isbn) {
        if (StringUtils.isEmpty(isbn)) {
            return NOT_EXISTS_ISBN;
        }

        String[] isbns = isbn.split(" ");

        if (isbns.length == 0) {
            return NOT_EXISTS_ISBN;
        }

        return createIsbnByApi(isbns);
    }

    public static Isbn createIsbnByApi(String[] isbns) {
        String isbn = isbns.length == 2 ? isbns[1] : isbns[0];
        return new Isbn(isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Isbn isbn1 = (Isbn) o;

        return isbn != null ? isbn.equals(isbn1.isbn) : isbn1.isbn == null;
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }

    @Override
    public String toString() {
        return isbn;
    }
}
