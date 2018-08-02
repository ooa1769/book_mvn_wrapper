package com.ooa1769.bs.member;

import com.ooa1769.bs.support.domain.UrlGeneratable;
import com.ooa1769.bs.support.jpa.AbstractEntity;
import com.ooa1769.bs.support.jpa.BooleanToYNConverter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name = "member", indexes = {@Index(name = "idx_member_email",  columnList="email", unique = true)})
@Convert(converter = BooleanToYNConverter.class, attributeName = "enabled")
public class Member extends AbstractEntity implements UrlGeneratable {

    public static final Member GUEST_MEMBER = new GuestMember();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Getter
    @Column(length = 60, nullable = false)
    private String email;

    @Getter
    @Column(length = 20, nullable = false)
    private String name;

    @Getter
    @Column(length = 60, nullable = false)
    private String password;

    @Getter
    @Column
    private boolean enabled = true;

    protected Member() {}

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name, String password, boolean enabled) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

    public Member(Long id, String email, String name, String password, boolean enabled) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

    public boolean isGuest() {
        return false;
    }

    static class GuestMember extends Member {
        @Override
        public boolean isGuest() {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return email != null ? email.equals(member.email) : member.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String generateRestUrl() {
        return String.format("/api/members/%d", id);
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", enabled=" + enabled + "]";
    }
}
