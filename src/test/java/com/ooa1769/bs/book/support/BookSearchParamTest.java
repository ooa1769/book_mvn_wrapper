package com.ooa1769.bs.book.support;

import com.ooa1769.bs.web.dto.BookSearchParam;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BookSearchParamTest {

    @Test
    public void 디폴트_페이지_사이즈_값_테스트() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        assertThat(bookSearchParam.getPage()).isEqualTo(1);
        assertThat(bookSearchParam.getSize()).isEqualTo(10);
    }

    @Test
    public void 페이지_사이즈_값_설정_테스트() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        bookSearchParam.setPage(5);
        bookSearchParam.setSize(10);
        assertThat(bookSearchParam.getPage()).isEqualTo(5);
        assertThat(bookSearchParam.getSize()).isEqualTo(10);
    }

    @Test
    public void 페이지_사이즈_MAX값_테스트() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        bookSearchParam.setPage(51);
        bookSearchParam.setSize(51);
        assertThat(bookSearchParam.getPage()).isEqualTo(1);
        assertThat(bookSearchParam.getSize()).isEqualTo(10);
    }

    @Test
    public void 페이지_사이즈_MIN값_테스트() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        bookSearchParam.setPage(0);
        bookSearchParam.setSize(0);
        assertThat(bookSearchParam.getPage()).isEqualTo(1);
        assertThat(bookSearchParam.getSize()).isEqualTo(10);
    }

    @Test
    public void 쿼리_파라미터_포함여부_확인() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        Map<String, String> params = bookSearchParam.queryParam();
        assertThat(params).containsKeys("page", "size");
        assertThat(params).doesNotContainKeys("target", "query", "category", "sort");

        bookSearchParam.setTarget("title");
        bookSearchParam.setQuery("스프링부트");
        params = bookSearchParam.queryParam();
        assertThat(params).containsKeys("page", "size", "target", "query");
        assertThat(params).doesNotContainKeys("category", "sort");
    }
}
