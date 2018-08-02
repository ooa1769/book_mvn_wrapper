package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.domain.*;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.web.dto.BookSearchParam;
import com.ooa1769.bs.book.support.search.SearchService;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.web.dto.BookMarkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final SearchService searchService;
    private final BookMarkRepository bookMarkRepository;

    @Autowired
    public BookService(SearchService searchService, BookMarkRepository bookMarkRepository) {
        this.searchService = searchService;
        this.bookMarkRepository = bookMarkRepository;
    }

    public Page<Book> getBooksByKeyword(ApiType apiType, BookSearchParam bookSearchParam) {
        return searchService.search(apiType, bookSearchParam);
    }

    public Book getBookByIsbn(ApiType apiType, BookSearchParam bookSearchParam) {
        Page<Book> pageBook = searchService.search(apiType, bookSearchParam);
        if (pageBook.getTotalElements() == 0) {
            throw new BookNotFoundException("해당 책의 상세정보가 존재하지 않습니다.");
        }
        return pageBook.getContent().get(0);
    }

    // ============== BookMark ============
    public BookMark addBookMark(BookMarkDto bookMarkDto, Member member) {
        BookMark bookMark = bookMarkDto.createBookMark(member);
        return bookMarkRepository.save(bookMark);
    }

    public void deleteBookMark(BookMark bookMark) {
        bookMarkRepository.delete(bookMark);
    }

    @Transactional(readOnly = true)
    public BookMark getBookMarkById(Long id) {
        return bookMarkRepository.findById(id)
                .orElseThrow(() -> new BookMarkNotFoundException("해당 북마크가 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public Page<BookMark> getBookMarksByMember(Member member, Pageable pageable) {
        return bookMarkRepository.findByMember(member, pageable);
    }

    @Transactional(readOnly = true)
    public boolean isAddedBookMarkByMemberAndIsbn(Member member, Isbn isbn) {
        return bookMarkRepository.findByMemberAndIsbn(member, isbn).isPresent();
    }

    @Transactional(readOnly = true)
    public boolean isAddedBookMarkByMemberAndIsbnAndApiType(Member member, Isbn isbn, ApiType apiType) {
        return bookMarkRepository.findByMemberAndIsbnAndApiType(member, isbn, apiType).isPresent();
    }
}
