package com.example.running.member.domain;

import com.example.running.member.dto.MemberRequestDto;
import com.example.running.member.dto.MemberResponseDto;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id", "username", "password", "name"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Ignore
    private String password;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private String email;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime create;

    @Builder
    public Member(String username, String password, String name, Set<Role> roles){
        this.username = username;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

    public void updateMember(MemberRequestDto dto){
        this.name = dto.getName ();
        this.email = dto.getEmail();
    }

    public void updatePassword(MemberRequestDto dto){
        this.password = dto.getPassword ();
    }

    public MemberResponseDto toResponseDto(){
        return new MemberResponseDto (this);
    }
}