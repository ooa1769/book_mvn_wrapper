package com.ooa1769.bs.web.dto;

public enum BookMarkSort {

    CREATE_DATE("날짜","createDate,desc", "createDate: DESC"),
    TITLE("제목","title,asc", "title: ASC");

    private String displayName;
    private String sort;
    private String sortDirection;

    BookMarkSort(String displayName, String sort, String sortDirection) {
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
