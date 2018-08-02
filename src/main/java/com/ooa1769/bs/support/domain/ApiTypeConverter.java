package com.ooa1769.bs.support.domain;

import com.ooa1769.bs.book.support.search.ApiType;
import org.springframework.core.convert.converter.Converter;

public class ApiTypeConverter implements Converter<String, ApiType> {

    public ApiType convert(String value) {
        return ApiType.code(value);
    }
}
