package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.web.dto.BookSearchParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(value = {"classpath:kakao.properties","classpath:naver.properties"})
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void 카카오_책_검색() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        bookSearchParam.setQuery("토비의 스프링");
        bookSearchParam.setTarget("title");

        Page<Book> books = bookService.getBooksByKeyword(ApiType.KAKAO, bookSearchParam);
        assertThat(books).isNotEmpty();
        assertThat(books.getNumber()).isEqualTo(0);
        assertThat(books.getSize()).isEqualTo(10);
    }

    @Test
    public void 네이버_책_검색() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        bookSearchParam.setQuery("토비의 스프링");
        bookSearchParam.setTarget("d_titl");
        bookSearchParam.setSort("date");

        Page<Book> books = bookService.getBooksByKeyword(ApiType.NAVER, bookSearchParam);
        //assertThat(books).isNotEmpty();
        //assertThat(books.getNumber()).isEqualTo(0);
        //assertThat(books.getSize()).isEqualTo(10);
    }
}
