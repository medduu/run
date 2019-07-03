package com.example.running;

import com.example.running.member.domain.Member;
import com.example.running.member.domain.Role;
import com.example.running.member.repository.MemberRepository;
import com.example.running.member.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RunningApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(MemberRepository memberRepository, RoleRepository roleRepository) {
        return args -> {
            Role role = roleRepository.save (new Role ("ROLE_MEMBER"));
            roleRepository.save (new Role ("ROLE_ADMIN"));

            memberRepository.save (new Member ("test11", "$2a$10$EMocO22.Qd3CVEScBIy6Bu9.XEMni1KW3A2TayjXzNPNOUH1X9r.i", "테스트", role));
        };
    }

}
