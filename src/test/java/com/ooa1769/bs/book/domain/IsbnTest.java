package com.ooa1769.bs.book.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class IsbnTest {

    @Test
    public void isbn_객체_생성() {
        Isbn isbn = new Isbn("9791131982082");
        assertThat(isbn).isEqualTo(new Isbn("9791131982082"));
    }

    @Test(expected = IsbnInvalidException.class)
    public void isbn_객체_생성_예외() {
        Isbn isbn = new Isbn("979113198208212312313");
        fail();
    }

    @Test
    public void isbn_문자열_검증_일반적인경우() {
        Isbn isbn = Isbn.createIsbnByApi("1131982088 9791131982082");
        assertThat(isbn).isEqualTo(new Isbn("9791131982082"));
    }

    @Test
    public void isbn_문자열_검증_예외케이스1() {
        Isbn isbn = Isbn.createIsbnByApi(" 2007917000121");
        assertThat(isbn).isEqualTo(new Isbn("2007917000121"));
    }

    @Test
    public void isbn_문자열_검증_예외케이스2() {
        Isbn isbn = Isbn.createIsbnByApi(" ");
        assertThat(isbn).isEqualTo(Isbn.NOT_EXISTS_ISBN);
    }
}
