package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.domain.SearchHistory;
import com.ooa1769.bs.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryService(SearchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public SearchHistory addSearchHistory(Member member, String searchKeyword) {
        Optional<SearchHistory> searchHistoryOpt= searchHistoryRepository.findBySearchKeyword(searchKeyword);
        searchHistoryOpt.ifPresent(history -> searchHistoryRepository.delete(history));
        SearchHistory searchHistory = new SearchHistory(searchKeyword, member);
        return searchHistoryRepository.save(searchHistory);
    }

    @Transactional(readOnly = true)
    public Page<SearchHistory> getHistoryByMember(Member member, Pageable pageable) {
        return searchHistoryRepository.findByMember(member, pageable);
    }
}
