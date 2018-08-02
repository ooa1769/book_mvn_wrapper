package com.ooa1769.bs.book.support.search.naver;

import com.ooa1769.bs.web.dto.BookSearchParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
public class NaverApiProperties {

    private final static String NAVER_AUTHORIZATION_HEADER_CLIENT_ID = "X-Naver-Client-Id";
    private final static String KAKAO_AUTHORIZATION_HEADER_CLIENT_SECRET = "X-Naver-Client-Secret";

    @Value("${naver.api.url}")
    private String url;

    @Value("${naver.api.detailUrl}")
    private String detailUrl;

    @Value("${naver.api.clientId}")
    private String clientId;

    @Value("${naver.api.clientSecret}")
    private String clientSecret;

    public String getHeaderClientId() {
        return NAVER_AUTHORIZATION_HEADER_CLIENT_ID;
    }

    public String getHeaderClientSecret() {
        return KAKAO_AUTHORIZATION_HEADER_CLIENT_SECRET;
    }

    public String baseUrl(BookSearchParam bookSearchParam) {
        String target = bookSearchParam.getTarget();
        return isAllSearchUrl(target) ? url : detailUrl;
    }

    public Map<String, String> queryParam(BookSearchParam bookSearchParam) {
        Map<String, String> params = new HashMap<>();
        addStartPage(params, bookSearchParam.getPage(), bookSearchParam.getSize());
        addDisplay(params, bookSearchParam.getSize());
        addSort(params, bookSearchParam.getSort());
        addTarget(params, bookSearchParam.getTarget(), bookSearchParam.getQuery());
        return params;
    }

    private void addStartPage(Map<String, String> params, int page, int size) {
        int startPage = ((page - 1) * size) + 1;
        params.put("start", startPage + "");
    }

    private void addDisplay(Map<String, String> params, int size) {
        params.put("display", size + "");
    }

    private void addSort(Map<String, String> params, String sort) {
        if (!StringUtils.isEmpty(sort)) {
            params.put("sort", sort);
        } else {
            params.put("sort", "sim");
        }
    }

    private void addTarget(Map<String, String> params, String target, String query) {
        if (isAllSearchUrl(target)) {
            params.put("query", query);
        } else {
            params.put(target, query);
        }
    }

    private boolean isAllSearchUrl(String target) {
        return StringUtils.isEmpty(target) || "all".equals(target);
    }
}
