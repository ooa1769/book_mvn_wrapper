package com.ooa1769.bs.book.support.search;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public class ApiUrlQueryBuilder {

    public static String urlForQueryParams(String url, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(URI.create(url));
        for (Map.Entry<String, String> param : params.entrySet()) {
            builder.queryParam(param.getKey(), param.getValue());
        }
        return builder.build().toUriString();
    }
}
