package com.example.running.member;

import com.example.running.member.domain.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleBuilder {
    public static Role build(){
        return createRole ("ROLE_MEMBER");
    }
    public static Role build(String roleName){
        return createRole (roleName);
    }

    private static Role createRole(String roleName) {
        return new Role (roleName);
    }

    public static Set<Role> getMemberRole() {
        Set<Role> roles = new HashSet<> ();
        roles.add(build());
        return roles;
    }

    public static Set<Role> getAdminRole() {
        Set<Role> roles = getMemberRole ();
        roles.add(build("ROLE_ADMIN"));
        return roles;
    }
}
