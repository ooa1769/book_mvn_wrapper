package com.ooa1769.bs.book.support.search.kakao;

import com.ooa1769.bs.support.domain.EnumMapperType;

public enum KakaoCategoryType implements EnumMapperType {

    C_ALL("전체", ""),
    C_1("국내도서", "소설"),
    C_3("국내도서", "시/에세이"),
    C_5("국내도서", "인문"),
    C_7("국내도서", "가정/생활"),
    C_8("국내도서", "요리"),
    C_9("국내도서", "건강"),
    C_11("국내도서", "취미/스포츠"),
    C_13("국내도서", "경제/경영"),
    C_15("국내도서", "자기계발"),
    C_17("국내도서", "정치/사회"),
    C_18("국내도서", "정부간행물"),
    C_19("국내도서", "역사/문화"),
    C_21("국내도서", "종교"),
    C_23("국내도서", "예술/대중문화"),
    C_25("국내도서", "중/고등학습"),
    C_26("국내도서", "기술/공학"),
    C_27("국내도서", "외국어"),
    C_29("국내도서", "과학"),
    C_31("국내도서", "취업/수험서"),
    C_32("국내도서", "여행/기행"),
    C_33("국내도서", "컴퓨터/IT"),
    C_35("국내도서", "잡지"),
    C_37("국내도서", "사전"),
    C_38("국내도서", "청소년"),
    C_39("국내도서", "초등참고서"),
    C_41("국내도서", "유아"),
    C_42("국내도서", "아동"),
    C_45("국내도서", "어린이영어"),
    C_47("국내도서", "만화"),
    C_50("국내도서", "대학교재"),
    C_51("국내도서", "어린이전집"),
    C_53("국내도서", "한국소개도서");

    private String large;
    private String medium;

    KakaoCategoryType(String large, String medium) {
        this.large = large;
        this.medium = medium;
    }

    @Override
    public String getCode() {
        return name().substring(2);
    }

    @Override
    public String getDisplayName() {
        return large + " " + medium;
    }
}


