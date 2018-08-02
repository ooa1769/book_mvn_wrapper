package com.ooa1769.bs.web.dto;

public enum SearchHistorySort {

    SEARCH_DATE("날짜","searchDate,desc", "searchDate: DESC"),
    SEARCH_KEYWORD("키워드","searchKeyword,asc", "searchKeyword: ASC");

    private String displayName;
    private String sort;
    private String sortDirection;

    SearchHistorySort(String displayName, String sort, String sortDirection) {
        this.displayName = displayName;
        this.sort = sort;
        this.sortDirection = sortDirection;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSort() {
        return sort;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public String getId() {
        return name();
    }

    @Override
    public String toString() {
        return getSort();
    }
}
