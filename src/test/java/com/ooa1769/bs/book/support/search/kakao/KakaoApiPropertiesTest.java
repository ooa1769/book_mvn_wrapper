package com.ooa1769.bs.book.support.search.kakao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KakaoApiPropertiesTest {

    KakaoApiProperties properties;

    @Before
    public void setUp() throws Exception {
        properties = new KakaoApiProperties();
        properties.setUrl("https://dapi.kakao.com/v2/search/book");
        properties.setKey("1abc24343adcf43asd6keka42");
    }

    @Test
    public void 카카오_api_url_확인() {
        assertEquals("https://dapi.kakao.com/v2/search/book", properties.getUrl());
    }

    @Test
    public void 인증_헤더_값_검증() {
        assertEquals("KakaoAK 1abc24343adcf43asd6keka42", properties.authorizationHeaderValue());
    }
}