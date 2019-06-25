package com.example.running.member.service;

import com.example.running.member.domain.Member;
import com.example.running.member.dto.MemberRequestDto;
import com.example.running.member.dto.MemberResponseDto;
import com.example.running.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RoleService roleService;

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long id) {
        return findById(id).toResponseDto ();
    }

    @Transactional(readOnly = true)
    private Member findById(Long id) {
        return memberRepository.findById (id).orElseThrow (() -> new NoSuchElementException ("등록되지 않은 Member 입니다."));
    }

    public MemberResponseDto signUp(MemberRequestDto dto) {
        if(memberRepository.existsByUsername (dto.getUsername ()) || memberRepository.existsByName (dto.getName ()))
            throw new IllegalArgumentException ("이미 등록된 아이디 or 이름 입니다.");
        return memberRepository.save(dto.toEntity (roleService.getMemberRoles ())).toResponseDto ();
    }

    public MemberResponseDto modifyMember(MemberRequestDto dto) {
        Member member = findById (dto.getId());
        member.updateMember (dto);

        return memberRepository.save(dto.toEntity (member.getRoles ())).toResponseDto ();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById (id);
    }
}
