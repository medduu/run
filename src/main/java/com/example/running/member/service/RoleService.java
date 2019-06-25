package com.example.running.member.service;

import com.example.running.member.domain.Role;
import com.example.running.member.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Set<Role> getMemberRoles(){
        Set<Role> roles = new HashSet<> ();
        roles.add(getRole("ROLE_MEMBER"));
        return roles;
    }

    public Set<Role> getAdminRoles(){
        Set<Role> roles = getMemberRoles ();
        roles.add(getRole("ROLE_ADMIN"));
        return roles;
    }

    public Role getRole(Long id){
        return roleRepository.getOne (id);
    }

    //TODO 이거 딴데로 빼버려
    public Role getRole(String name){
        Optional<Role> role = roleRepository.findByName (name);
        if(role.isPresent ()) {
            return role.get ( );
        }else {
            init();
            return getRole(name);
        }
    }

    private void init(){
        roleRepository.save(new Role("ROLE_MEMBER"));
        roleRepository.save(new Role("ROLE_ADMIN"));
    }
}
