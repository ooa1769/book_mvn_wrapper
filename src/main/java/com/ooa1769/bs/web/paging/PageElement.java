package com.ooa1769.bs.web.paging;

import lombok.Getter;

@Getter
public class PageElement {

    private int pageNumber;
    private boolean isCurrentPage;

    public PageElement(int pageNumber, boolean isCurrentPage) {
        this.pageNumber = pageNumber;
        this.isCurrentPage = isCurrentPage;
    }

    @Override
    public String toString() {
        return "PageElement[" + "pageNumber=" + pageNumber + ", isCurrentPage=" + isCurrentPage + ']';
    }
}
