package com.ooa1769.bs.support.security;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";

    private final MemberRepository memberRepository;

    @Autowired
    public SecurityUserDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        Member member = memberOpt.orElseThrow(
                () -> new UsernameNotFoundException("회원이 존재하지 않습니다."));

        return new User(member.getEmail(), member.getPassword(), member.isEnabled(), true, true, true, getAuthorities(ROLE_USER));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
