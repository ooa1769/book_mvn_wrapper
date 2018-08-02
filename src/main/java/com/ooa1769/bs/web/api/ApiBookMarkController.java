package com.ooa1769.bs.web.api;

import com.ooa1769.bs.book.domain.BookMark;
import com.ooa1769.bs.book.domain.Isbn;
import com.ooa1769.bs.book.support.BookService;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.support.security.LoginMember;
import com.ooa1769.bs.support.util.Mappings;
import com.ooa1769.bs.web.dto.BookMarkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(Mappings.API_BOOKMARKS)
public class ApiBookMarkController {

    private final BookService bookService;

    @Autowired
    public ApiBookMarkController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> addBookMark(@LoginMember Member member, @RequestBody BookMarkDto bookMarkDto) {
        BookMark bookMark = bookService.addBookMark(bookMarkDto, member);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(bookMark.generateRestUrl()));
        return new ResponseEntity<>(new GenericResponse("success"), headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookMark(@PathVariable Long id) {
        BookMark bookMark = bookService.getBookMarkById(id);
        bookService.deleteBookMark(bookMark);
        return new ResponseEntity<>(new GenericResponse("success"), new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getBookMarks(@LoginMember Member member, @RequestParam(defaultValue = "1") int page) {
        PageRequest pageRequest = new PageRequest(page - 1, 10);
        return ResponseEntity.ok(bookService.getBookMarksByMember(member, pageRequest));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> isAddedBookMark(@LoginMember Member member, @PathVariable Isbn isbn, ApiType apiType) {
        boolean isAdded = bookService.isAddedBookMarkByMemberAndIsbnAndApiType(member, isbn, apiType);
        GenericResponse body =  isAdded ? new GenericResponse("exist", "이미 등록된 북마크입니다.") : new GenericResponse("not_exist");
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
    }
}
