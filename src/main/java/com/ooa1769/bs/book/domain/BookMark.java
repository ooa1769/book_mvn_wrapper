package com.ooa1769.bs.book.domain;

import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.support.domain.UrlGeneratable;
import com.ooa1769.bs.support.jpa.AbstractEntity;
import com.ooa1769.bs.support.util.Mappings;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "bookmark")
public class BookMark extends AbstractEntity implements UrlGeneratable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    @Getter
    private Long id;

    @Getter
    @Embedded
    private Isbn isbn;

    @Getter
    @Enumerated(EnumType.STRING)
    private ApiType apiType;

    @Getter
    private String title;

    @Getter
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public BookMark() {
    }

    public BookMark(Isbn isbn, String title, ApiType apiType, String url, Member member) {
        this.isbn = isbn;
        this.title = title;
        this.member = member;
        this.apiType = apiType;
        this.url = url;
    }

    @Override
    public String generateRestUrl() {
        return String.format(Mappings.BOOKMARKS_CREATE_FORMAT, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookMark bookMark = (BookMark) o;

        if (isbn != null ? !isbn.equals(bookMark.isbn) : bookMark.isbn != null) return false;
        return member != null ? member.equals(bookMark.member) : bookMark.member == null;

    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (member != null ? member.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookMark [id=" + id + ", isbn=" + isbn + ", title=" + title + "]";
    }
}
