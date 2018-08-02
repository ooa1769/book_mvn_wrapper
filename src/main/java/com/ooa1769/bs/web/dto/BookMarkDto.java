package com.ooa1769.bs.web.dto;

import com.ooa1769.bs.book.domain.BookMark;
import com.ooa1769.bs.book.domain.Isbn;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkDto {

    private String isbn;
    private String title;
    private String apiType;
    private String url;

    public BookMark createBookMark(Member member) {
        return new BookMark(toIsbn(isbn), title, ApiType.code(apiType), url, member);
    }

    public Isbn toIsbn(String isbn) {
        return new Isbn(isbn);
    }

    @Override
    public String toString() {
        return "BookMarkDto [isbn=" + isbn + ", title=" + title + "]";
    }
}
