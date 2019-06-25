package com.example.running.member.repository;

import com.example.running.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    boolean existsByUsername(String username);
    boolean existsByName(String name);
}
