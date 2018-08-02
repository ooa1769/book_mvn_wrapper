package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.domain.SearchHistory;
import com.ooa1769.bs.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    Page<SearchHistory> findByMember(Member member, Pageable pageable);
    Optional<SearchHistory> findBySearchKeyword(String searchKeyword);
}
