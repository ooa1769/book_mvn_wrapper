package com.ooa1769.bs.web.paging;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PagingInfo {

    @Getter
    private int startPage;

    @Getter
    private int endPage;
    private Page<?> page;

    public PagingInfo(Page<?> page) {
        this.page = page;
        this.endPage = endPage();
        this.startPage =  (tempEndPage() - page.getSize()) + 1;
    }

    public boolean isPreviousVisible() {
        return startPage != 1;
    }

    public boolean isNextVisible() {
        return endPage * page.getSize() < page.getTotalElements();
    }

    public List<PageElement> getPageElements() {
        List<PageElement> elements = new ArrayList<>();

        int currentPage = page.getNumber() + 1;
        for (int i = startPage; i <= endPage; i++) {
            elements.add(new PageElement(i, i == currentPage));
        }

        return elements;
    }

    public int getTotalElements() {
        return (int) page.getTotalElements();
    }

    private int endPage() {
        int endPage = tempEndPage();
        return endPage > page.getTotalPages() ? page.getTotalPages() : endPage;
    }

    private int tempEndPage() {
        int currentPage = page.getNumber() + 1;
        return (int) (Math.ceil(currentPage / (double) page.getSize()) * page.getSize());
    }

    @Override
    public String toString() {
        return "PagingInfo[" + "startPage=" + startPage + ", endPage=" + endPage + ", isPrev=" + isPreviousVisible() + ", isNext=" + isNextVisible() + ", pageElements=" + getPageElements() + "]";
    }
}
