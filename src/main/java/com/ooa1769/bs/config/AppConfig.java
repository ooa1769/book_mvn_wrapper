package com.ooa1769.bs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ooa1769.bs.book.support.search.ApiSearchType;
import com.ooa1769.bs.book.support.search.kakao.KakaoCategoryType;
import com.ooa1769.bs.book.support.search.kakao.KakaoSortType;
import com.ooa1769.bs.book.support.search.kakao.KakaoTargetType;
import com.ooa1769.bs.book.support.search.naver.NaverSortType;
import com.ooa1769.bs.book.support.search.naver.NaverTargetType;
import com.ooa1769.bs.support.domain.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
                .json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .dateFormat(new ISO8601DateFormat())
                .build();
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        converters.add(converter);
        return converters;
    }

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put(ApiSearchType.KAKAO_TARGET, KakaoTargetType.class);
        enumMapper.put(ApiSearchType.KAKAO_CATEGORY, KakaoCategoryType.class);
        enumMapper.put(ApiSearchType.KAKAO_SORT, KakaoSortType.class);
        enumMapper.put(ApiSearchType.NAVER_TARGET, NaverTargetType.class);
        enumMapper.put(ApiSearchType.NAVER_SORT, NaverSortType.class);
        return enumMapper;
    }
}
