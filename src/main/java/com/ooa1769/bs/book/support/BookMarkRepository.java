package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.domain.BookMark;
import com.ooa1769.bs.book.domain.Isbn;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    Optional<BookMark> findById(Long id);
    Optional<BookMark> findByMemberAndIsbn(Member member, Isbn isbn);
    Optional<BookMark> findByMemberAndIsbnAndApiType(Member member, Isbn isbn, ApiType apiType);
    Page<BookMark> findByMember(Member member, Pageable pageRequest);
}
