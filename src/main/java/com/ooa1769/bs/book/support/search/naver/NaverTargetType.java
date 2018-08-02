package com.ooa1769.bs.book.support.search.naver;

import com.ooa1769.bs.support.domain.EnumMapperType;

public enum NaverTargetType implements EnumMapperType {

    ALL("전체"),
    D_TITL("제목"),
    D_AUTH("저자명"),
    D_CONT("목차"),
    D_ISBN("isbn"),
    D_PUBL("출판"),
    D_DAFR("출간 시작일"),
    D_DATO("출간 종료일");

    private String target;

    NaverTargetType(String target) {
        this.target = target;
    }

    @Override
    public String getCode() {
        return name().toLowerCase();
    }

    @Override
    public String getDisplayName() {
        return target;
    }
}
