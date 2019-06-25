package com.example.running.member.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString(exclude = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id", "name"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Member> users;

    public Role(String name){
        this.name = name;
    }

}