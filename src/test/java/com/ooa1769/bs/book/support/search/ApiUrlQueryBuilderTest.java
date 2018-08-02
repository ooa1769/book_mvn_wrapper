package com.ooa1769.bs.book.support.search;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUrlQueryBuilderTest {

    @Test
    public void 카카오API_호출할_쿼리문자열_검증() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("size", "10");
        params.put("query", "spring");
        params.put("target", "title");
        params.put("category", "1");
        params.put("sort", "title");
        String url = "https://dapi.kakao.com/v2/search/book";
        String result = ApiUrlQueryBuilder.urlForQueryParams(url, params);
        assertThat(result).isEqualTo("https://dapi.kakao.com/v2/search/book?size=10&query=spring&page=1&sort=title&category=1&target=title");
    }
}