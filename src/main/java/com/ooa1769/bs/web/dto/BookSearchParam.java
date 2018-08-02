package com.ooa1769.bs.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BookSearchParam {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_MIN_PAGE = 1;
    private static final int DEFAULT_MAX_PAGE = 50;
    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_MIN_SIZE = 1;
    private static final int DEFAULT_MAX_SIZE = 50;

    private int page;
    private int size;
    private String query;
    private String target;
    private String category;
    private String sort;

    public BookSearchParam() {
        this.page = DEFAULT_PAGE;
        this.size = DEFAULT_SIZE;
    }

    public void setPage(int page) {
        this.page = page < DEFAULT_MIN_PAGE || page > DEFAULT_MAX_PAGE ? DEFAULT_PAGE : page;
    }

    public void setSize(int size) {
        this.size = size < DEFAULT_MIN_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public Pageable pageable() {
        return new PageRequest(page - 1, size);
    }

    public Map<String, String> queryParam() {
        Map<String, String> params = new HashMap<>();
        addPage(params);
        addSize(params);
        addQuery(params);
        addTarget(params);
        addCategory(params);
        addSort(params);
        return params;
    }

    private void addPage(Map<String, String> params) {
        if (!ObjectUtils.isEmpty(page)) {
            params.put("page", page + "");
        }
    }

    private void addSize(Map<String, String> params) {
        if (!ObjectUtils.isEmpty(size)) {
            params.put("size", size + "");
        }
    }

    private void addQuery(Map<String, String> params) {
        if (!ObjectUtils.isEmpty(query)) {
            params.put("query", query);
        }
    }

    private void addTarget(Map<String, String> params) {
        if (!ObjectUtils.isEmpty(target)) {
            params.put("target", target);
        }
    }

    private void addCategory(Map<String, String> params) {
        if (!ObjectUtils.isEmpty(category) && !"ALL".equals(category)) {
            params.put("category", category);
        }
    }

    private void addSort(Map<String, String> params) {
        if (!ObjectUtils.isEmpty(sort)) {
            params.put("sort", sort);
        }
    }

    @Override
    public String toString() {
        return "BookSearchParam [page=" + page + ", size=" + size + ", query=" + query + ", target=" + target + ", category=" + category + ", sort=" + sort + "]";
    }
}
