package com.ooa1769.bs.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrevBookSearchParam {

    private int prevPage;
    private int prevSize;
    private String prevTarget;
    private String prevQuery;
    private String prevCategory;
    private String prevSort;
}
