package com.example.running.member.repository;

import com.example.running.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByName(String name);
}
