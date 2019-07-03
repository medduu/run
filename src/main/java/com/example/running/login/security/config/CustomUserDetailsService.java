package com.example.running.login.security.config;

import com.example.running.member.domain.Member;
import com.example.running.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername (username).orElseThrow (()->new UsernameNotFoundException("등록되지 않은 멤버 입니다."));
        return User.builder ()
                .username (member.getUsername ())
                .password (member.getPassword ())
                .authorities (member.getRoles ()).build ();
    }
}
