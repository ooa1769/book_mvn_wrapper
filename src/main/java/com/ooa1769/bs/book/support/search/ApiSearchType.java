package com.ooa1769.bs.book.support.search;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ApiSearchType {

    KAKAO_TARGET(ApiType.KAKAO, SearchType.TARGET),
    KAKAO_CATEGORY(ApiType.KAKAO, SearchType.CATEGORY),
    KAKAO_SORT(ApiType.KAKAO, SearchType.SORT),
    NAVER_TARGET(ApiType.NAVER, SearchType.TARGET),
    NAVER_SORT(ApiType.NAVER, SearchType.SORT);

    private ApiType apiType;
    private SearchType searchType;

    ApiSearchType(ApiType apiType, SearchType searchType) {
        this.apiType = apiType;
        this.searchType = searchType;
    }

    public static ApiSearchType getApiSearchType(ApiType apiType, SearchType searchType) {
        return Arrays.stream(values())
                .filter(ast -> ast.getApiType() == apiType)
                .filter(ast -> ast.getSearchType() == searchType)
                .findFirst()
                .orElse(null);
    }
}
