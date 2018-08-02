package com.ooa1769.bs.member.support;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.MemberAlreadyExistException;
import com.ooa1769.bs.web.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Member register(MemberDto memberDto) {
        if (emailExist(memberDto.getEmail())) {
            throw new MemberAlreadyExistException(memberDto.getEmail() + "은 이미 등록된 이메일입니다.");
        }
        String encodePassword = passwordEncoder.encode(memberDto.getPassword());
        log.debug("encodePassword = {}", encodePassword);
        memberDto.setPassword(encodePassword);
        return memberRepository.save(memberDto.toMember());
    }

    private boolean emailExist(final String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}
