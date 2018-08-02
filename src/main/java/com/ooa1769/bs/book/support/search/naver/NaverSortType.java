package com.ooa1769.bs.book.support.search.naver;

import com.ooa1769.bs.support.domain.EnumMapperType;

public enum NaverSortType implements EnumMapperType {

    SIM("유사도순"),
    DATE("출간일순"),
    COUNT("판매량순");

    private String sort;

    NaverSortType(String sort) {
        this.sort = sort;
    }

    @Override
    public String getCode() {
        return name().toLowerCase();
    }

    @Override
    public String getDisplayName() {
        return sort;
    }
}
