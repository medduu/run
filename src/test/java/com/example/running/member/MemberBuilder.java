package com.example.running.member;

import com.example.running.member.domain.Member;
import com.example.running.member.domain.Role;

import java.util.Set;

public class MemberBuilder {
    public static Member memberBuild(){
        String username = "testMember";
        String password = "1111";
        String name = "member";
        Set<Role> roles = RoleBuilder.getMemberRole();
        return createMember(username, password, name, roles);
    }

    public static Member adminBuild(){
        String username = "testAdmin";
        String password = "1234";
        String name = "admin";
        Set<Role> roles = RoleBuilder.getAdminRole();
        return createMember(username, password, name, roles);
    }

    public static Member build(String username, String password, String name, Set<Role> roles){
        return createMember (username, password, name, roles);
    }

    private static Member createMember(String username, String password, String name, Set<Role> roles) {
        return new Member(username, password, name, roles);
    }
}
