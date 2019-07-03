package com.example.running.member.domain;

import com.example.running.member.dto.MemberRequestDto;
import com.example.running.member.dto.MemberResponseDto;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id", "username", "password", "name"})
public class Member implements Serializable {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder ();

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

    public Member(String username, String password, String name, Role role){
        this.username = username;
        this.password = password;
        this.name = name;
        setRoles(role);
    }

    private void setRoles(Role role){
        Set<Role> roles = new HashSet<> ();
        roles.add (role);
        this.roles = roles;
    }

    public void updateMember(MemberRequestDto dto){
        updatePassword(dto.getPassword ());
        this.name = dto.getName ();
        this.email = dto.getEmail();
    }

    private void updatePassword(String password){
        if(Objects.isNull (password) || password.trim ().isEmpty ())
            return;
        this.password = password;
    }

    public MemberResponseDto toResponseDto(){
        return new MemberResponseDto (this);
    }
}