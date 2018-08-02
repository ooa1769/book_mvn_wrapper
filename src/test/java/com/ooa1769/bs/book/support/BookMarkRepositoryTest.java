package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.domain.BookMark;
import com.ooa1769.bs.book.domain.Isbn;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.member.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookMarkRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookMarkRepository bookMarkRepository;

    private Member member;

    @Before
    public void setUp() throws Exception {
        member = new Member("ggulmool@naver.com", "김남열", "1234", true);
    }

    @Test
    public void 북마크_추가_목록조회() {
        entityManager.persist(member);
        entityManager.flush();

        BookMark bookMark1 = new BookMark(new Isbn("9791188621279"), "스프링 부트로 배우는 자바 웹 개발", ApiType.KAKAO, "url1", member);
        BookMark bookMark2 = new BookMark(new Isbn("9788960777330"), "자바 ORM 표준 JPA 프로그래밍", ApiType.KAKAO, "url2", member);

        bookMarkRepository.save(bookMark1);
        bookMarkRepository.save(bookMark2);

        Pageable pageable = new PageRequest(0, 10, Sort.Direction.ASC, "title");

        Page<BookMark> bookMarks = bookMarkRepository.findByMember(member, pageable);
        assertThat(bookMarks.getContent()).size().isEqualTo(2);

        assertThat(bookMarks.getNumber()).isEqualTo(0); // 페이지
        assertThat(bookMarks.getSize()).isEqualTo(10); // 한페이지에 보여줄 건수
        assertThat(bookMarks.getTotalPages()).isEqualTo(1); // 총 페이지 수
        assertThat(bookMarks.getTotalElements()).isEqualTo(2); // 총 건수
    }
}
