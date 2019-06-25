package com.example.running.member.dto;

import com.example.running.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String username;
    private String name;

    public MemberResponseDto(Member member){
        id = member.getId ();
        username = member.getUsername ();
        name = member.getName ();
    }
}
