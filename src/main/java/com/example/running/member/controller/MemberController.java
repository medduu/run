package com.example.running.member.controller;

import com.example.running.member.dto.MemberRequestDto;
import com.example.running.member.dto.MemberResponseDto;
import com.example.running.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public MemberResponseDto getMember(@PathVariable Long id){
        return memberService.getMember (id);
    }

    @PostMapping
    public MemberResponseDto singUp(@RequestBody MemberRequestDto dto){
        return memberService.signUp(dto);
    }

    @PutMapping
    public MemberResponseDto modifyMember(@RequestBody MemberRequestDto dto){
        return memberService.modifyMember(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
    }
}
