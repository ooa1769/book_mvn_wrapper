package com.ooa1769.bs.book.support.search;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.web.dto.BookSearchParam;
import org.springframework.data.domain.Page;

public interface BookSearchClient {

    Page<Book> search(BookSearchParam bookSearchParam);
}
