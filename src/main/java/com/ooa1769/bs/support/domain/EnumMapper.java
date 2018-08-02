package com.ooa1769.bs.support.domain;

import com.ooa1769.bs.book.support.search.ApiSearchType;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.book.support.search.SearchType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumMapper {

    private Map<ApiSearchType, List<EnumMapperValue>> factory = new HashMap<>();

    public void put(ApiSearchType apiSearchType, Class<? extends EnumMapperType> clazz) {
        factory.put(apiSearchType, toEnumValues(clazz));
    }

    public List<EnumMapperValue> targetTypeValues(ApiType apiType) {
        return get(ApiSearchType.getApiSearchType(apiType, SearchType.TARGET));
    }

    public List<EnumMapperValue> categoryTypeValues(ApiType apiType) {
        return get(ApiSearchType.getApiSearchType(apiType, SearchType.CATEGORY));
    }

    public List<EnumMapperValue> sortTypeValues(ApiType apiType) {
        return get(ApiSearchType.getApiSearchType(apiType, SearchType.SORT));
    }

    public List<EnumMapperValue> get(ApiSearchType apiSearchType) {
        return factory.get(apiSearchType);
    }

    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> clazz) {
        return Arrays.stream(clazz.getEnumConstants())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }
}
