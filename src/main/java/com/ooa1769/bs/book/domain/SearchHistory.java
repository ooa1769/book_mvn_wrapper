package com.ooa1769.bs.book.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ooa1769.bs.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String searchKeyword;

    @Getter
    @Column(nullable = false)
    private LocalDateTime searchDate;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected SearchHistory() {}

    public SearchHistory(String searchKeyword, Member member) {
        this.searchKeyword = searchKeyword;
        this.member = member;
        this.searchDate = LocalDateTime.now();
    }

    public SearchHistory(String searchKeyword, Member member, LocalDateTime searchDate) {
        this.searchKeyword = searchKeyword;
        this.member = member;
        this.searchDate = searchDate;
    }

    @JsonIgnore
    public String getFormattedSearchDate() {
        return getFormattedDate(searchDate, "yyyy.MM.dd HH:mm:ss");
    }

    private String getFormattedDate(LocalDateTime dateTime, String format) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchHistory)) return false;
        SearchHistory that = (SearchHistory) o;
        return Objects.equals(searchKeyword, that.searchKeyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchKeyword);
    }

    @Override
    public String toString() {
        return "SearchHistory [searchKeyword=" + searchKeyword + ", searchDate=" + searchDate + "]";
    }
}
