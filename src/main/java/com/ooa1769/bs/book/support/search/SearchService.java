package com.ooa1769.bs.book.support.search;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.web.dto.BookSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    private Map<ApiType, BookSearchClient> bookSearchClients = new HashMap<>();

    @Autowired
    public SearchService(@Qualifier("kakao") BookSearchClient kakaoBookSearchClient,
                         @Qualifier("naver") BookSearchClient naverBookSearchClient) {
        bookSearchClients.put(ApiType.KAKAO, kakaoBookSearchClient);
        bookSearchClients.put(ApiType.NAVER, naverBookSearchClient);
    }

    public Page<Book> search(ApiType apiType, BookSearchParam bookSearchParam) {
        BookSearchClient bookSearchClient = bookSearchClients.get(apiType);
        return bookSearchClient.search(bookSearchParam);
    }
}
