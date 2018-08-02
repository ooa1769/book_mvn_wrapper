package com.ooa1769.bs.web.dto;

import com.ooa1769.bs.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class MemberDto {

    @NotEmpty(message = "이메일은 필수값입니다.")
    private String email;

    @NotEmpty(message = "패스워드는 필수값입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수값입니다.")
    private String name;

    public Member toMember() {
        return new Member(email, name, password, true);
    }

    @Override
    public String toString() {
        return "MemberDto [email=" + email + ", name=" + name + ", password=" + password + "]";
    }
}
