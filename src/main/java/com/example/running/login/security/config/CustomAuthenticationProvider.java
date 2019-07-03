package com.example.running.login.security.config;

import com.example.running.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        String decodeName = "";
        String decodePassword = "";

        UserDetails user;
        Collection<? extends GrantedAuthority> authorities;
        try {

            /*PrivateKey privateKey = (PrivateKey)session.getAttribute(RSAService.RSA_WEB_KEY);
            decodeName = rsaService.decryptRsa(privateKey, username);
            decodePassword = rsaService.decryptRsa(privateKey, password);

            user = customUserDetailsService.loadUserByUsername(decodeName);*/

            user = customUserDetailsService.loadUserByUsername(username);

            if (!Member.PASSWORD_ENCODER.matches(password, user.getPassword()))
                throw new BadCredentialsException ("비밀번호가 일치하지 않습니다.");

            authorities = user.getAuthorities();


        } catch(UsernameNotFoundException e) {
            throw new UsernameNotFoundException (e.getMessage());

        } catch(BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());

        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
