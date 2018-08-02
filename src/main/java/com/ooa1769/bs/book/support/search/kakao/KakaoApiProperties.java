package com.ooa1769.bs.book.support.search.kakao;

import com.ooa1769.bs.web.dto.BookSearchParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

@Getter
@Setter
@Component
public class KakaoApiProperties {

    private final static String KAKAO_AUTHORIZATION_HEADER = "KakaoAK";

    @Value("${kakao.api.key}")
    private String key;

    @Value("${kakao.api.url}")
    private String url;

    public String authorizationHeaderValue() {
        Assert.notNull(key, "api key not null");
        return String.format("%s %s",KAKAO_AUTHORIZATION_HEADER, key);
    }

    public Map<String,String> queryParam(BookSearchParam bookSearchParam) {
        return bookSearchParam.queryParam();
    }
}