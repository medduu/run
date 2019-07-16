package com.example.running.login.controller;

import com.example.running.login.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/login")
public class LoginApiController {

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
        String username = authenticationRequest.getUsername ();
        String password = authenticationRequest.getPassword ();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        return new ResponseEntity (session.getId (), HttpStatus.OK);
    }
}
