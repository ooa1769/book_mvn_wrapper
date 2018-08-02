package com.ooa1769.bs.book.support.search.naver;

import com.ooa1769.bs.web.dto.BookSearchParam;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class NaverApiPropertiesTest {

    private NaverApiProperties apiProperties;

    @Before
    public void setUp() throws Exception {
        apiProperties = new NaverApiProperties();
        apiProperties.setUrl("https://openapi.naver.com/v1/search/book.json");
        apiProperties.setClientId("99uFekoH2IOnqitrXblg");
        apiProperties.setClientSecret("sAtwPDzE_0");
    }

    @Test
    public void 네이버_api_url_확인() {
        assertEquals("https://openapi.naver.com/v1/search/book.json", apiProperties.getUrl());
    }

    @Test
    public void 클라이언트_id_secret_값_검증() {
        assertThat(apiProperties.getClientId()).isEqualTo("99uFekoH2IOnqitrXblg");
        assertThat(apiProperties.getClientSecret()).isEqualTo("sAtwPDzE_0");
    }

    @Test
    public void 쿼리_파라미터_포함여부_확인() throws Exception {
        BookSearchParam bookSearchParam = new BookSearchParam();
        Map<String, String> params = apiProperties.queryParam(bookSearchParam);
        assertThat(params).containsKeys("start", "display", "sort");

        bookSearchParam.setTarget("d_titl");
        bookSearchParam.setQuery("스프링부트");
        params = apiProperties.queryParam(bookSearchParam);
        assertThat(params).containsKeys("start", "display", "sort", "d_titl");
        assertThat(params).doesNotContainKeys("query");
    }
}
