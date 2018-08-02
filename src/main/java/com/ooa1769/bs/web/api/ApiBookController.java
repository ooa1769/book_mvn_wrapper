package com.ooa1769.bs.web.api;

import com.ooa1769.bs.book.support.BookService;
import com.ooa1769.bs.book.support.SearchHistoryService;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.web.dto.BookSearchParam;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.support.security.LoginMember;
import com.ooa1769.bs.support.util.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Mappings.API_BOOKS)
public class ApiBookController {

    private final BookService bookService;
    private final SearchHistoryService searchHistoryService;

    @Autowired
    public ApiBookController(BookService bookService, SearchHistoryService searchHistoryService) {
        this.bookService = bookService;
        this.searchHistoryService = searchHistoryService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@LoginMember Member member, BookSearchParam bookSearchParam) {
        return new ResponseEntity<>(bookService.getBooksByKeyword(ApiType.KAKAO, bookSearchParam), HttpStatus.OK);
    }
}
