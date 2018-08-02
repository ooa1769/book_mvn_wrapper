package com.ooa1769.bs.book.support.search.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.book.domain.Isbn;
import com.ooa1769.bs.book.domain.Price;
import com.ooa1769.bs.book.domain.SaleStatus;
import com.ooa1769.bs.book.support.search.ApiUrlQueryBuilder;
import com.ooa1769.bs.book.support.search.BookSearchClient;
import com.ooa1769.bs.book.support.search.SearchException;
import com.ooa1769.bs.web.dto.BookSearchParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Qualifier("naver")
public class NaverBookSearchClient implements BookSearchClient {

    private final RestTemplate restTemplate;
    private final NaverApiProperties properties;

    @Autowired
    public NaverBookSearchClient(RestTemplate restTemplate, NaverApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public Page<Book> search(BookSearchParam bookSearchParam) {
        if (StringUtils.isEmpty(bookSearchParam.getQuery())) {
            return new PageImpl<>(Collections.emptyList());
        }

        String url = ApiUrlQueryBuilder.urlForQueryParams(properties.baseUrl(bookSearchParam), properties.queryParam(bookSearchParam));
        Optional<NaverBookSearchClient.SearchResult> resultOpt = execute(url);

        if (resultOpt.isPresent()) {
            NaverBookSearchClient.SearchResult searchResult = resultOpt.get();
            List<Book> books = searchResult.getItems().stream()
                    .map(this::convertBook)
                    .collect(Collectors.toList());

            log.debug("books count : {}, pageableCount : {}", books.size(), searchResult.getTotal());
            return new PageImpl<>(books,
                    bookSearchParam.pageable(),
                    searchResult.getTotal());
        }

        return new PageImpl<>(Collections.emptyList());
    }

    private Optional<NaverBookSearchClient.SearchResult> execute(String url) {
        ResponseEntity<NaverBookSearchClient.SearchResult> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(url,
                    HttpMethod.GET,
                    getHttpHeaders(),
                    NaverBookSearchClient.SearchResult.class);
        } catch (RestClientException e) {
            log.error("RestClientException {}", e.getMessage());
            throw new SearchException("네이버 책 검색 API 호출 중 오류가 발생하였습니다.");
        }

        return Optional.ofNullable(responseEntity.getBody());
    }

    private HttpEntity<?> getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(properties.getHeaderClientId(), properties.getClientId());
        headers.set(properties.getHeaderClientSecret(), properties.getClientSecret());
        return new HttpEntity<>(headers);
    }

    private Book convertBook(NaverBookSearchClient.SearchResult.Item item) {
        return Book.builder()
                .title(item.toTitle())
                .contents(item.getDescription())
                .url(item.getLink())
                .isbn(item.toIsbn())
                .datetime(item.toZonedDateTime())
                .authors(item.toAuthors())
                .publisher(item.getPublisher())
                .price(item.toPrice())
                .thumbnail(item.getImage())
                .saleStatus(item.toSaleStatus())
                .build();
    }

    @Getter
    @NoArgsConstructor
    static class SearchResult {
        private Integer total;
        private Integer start;
        private Integer display;
        private List<Item> items;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Item {
            private String title;
            private String link;
            private String image;
            private String author;
            private Integer price;
            private Integer discount;
            private String publisher;
            private Date pubdate;
            private String isbn;
            private String description;

            Isbn toIsbn() {
                return Isbn.createIsbnByApi(isbn);
            }

            Price toPrice() {
                return new Price(price, ObjectUtils.isEmpty(discount) ? 0 : discount);
            }

            List<String> toAuthors() {
                String authors = replaceTag(author);
                return Arrays.asList(authors);
            }

            String toTitle() {
                return replaceTag(title);
            }

            ZonedDateTime toZonedDateTime() {
                return ObjectUtils.isEmpty(pubdate) ? null :
                        ZonedDateTime.ofInstant(pubdate.toInstant(), ZoneId.systemDefault());
            }

            SaleStatus toSaleStatus() {
                return ObjectUtils.isEmpty(discount) ? SaleStatus.N : SaleStatus.Y;
            }

            String replaceTag(String item) {
                return item.replace("<b>", "")
                        .replace("</b>", "");
            }
        }
    }
}
