package com.example.running.member.dto;

import com.example.running.member.domain.Member;
import com.example.running.member.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private Long id;

    @NotBlank(message = "아이디를 입력해 주세요.")
    private String username;

    @Pattern(regexp = "[A-Za-z0-9]{6,12}", message = "6~12자리의 숫자와 문자를 입력해 주세요.")
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    private String email;

    public MemberRequestDto(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public MemberRequestDto(Long id, String username, String password, String name){
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Member toEntity(Set<Role> roles){
        return new Member(username, password, name, roles);
    }

}
