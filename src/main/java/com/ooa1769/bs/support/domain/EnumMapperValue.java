package com.ooa1769.bs.support.domain;

public class EnumMapperValue {

    private String code;
    private String displayName;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        code = enumMapperType.getCode();
        displayName = enumMapperType.getDisplayName();
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
