package com.example.running.home.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping
    public String printClientIP(HttpServletRequest request) {
        return getClientIP (request);
    }

    private String getClientIP(HttpServletRequest request){
        String clientIp = request.getHeader("X-Forwarded-For");
        if(StringUtils.isEmpty (clientIp))
            clientIp = request.getRemoteAddr ();
        return clientIp;
    }
}
