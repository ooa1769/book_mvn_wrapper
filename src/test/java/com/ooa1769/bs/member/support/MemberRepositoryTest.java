package com.ooa1769.bs.member.support;

import com.ooa1769.bs.member.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @Before
    public void setup() {
        member = new Member("ggulmool@naver.com", "김남열", "1234", true);
    }

    @Test
    public void 회원_등록() {
        // given
        entityManager.persist(member);
        entityManager.flush();

        // when
        Member foundMember = memberRepository.findByEmail("ggulmool@naver.com").get();

        // then
        assertThat(foundMember.getName())
                .isEqualTo(member.getName());
    }
}
