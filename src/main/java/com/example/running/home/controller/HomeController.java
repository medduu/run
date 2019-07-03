package com.example.running.home.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;

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
        return clientIp + "에서 접속, " + getLocalServerIP ();
    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "Deploy Success";
    }

    private String getLocalServerIP(){
        try{
            return "Server IP : " + InetAddress.getLocalHost ().getHostAddress();
        } catch(UnknownHostException e){
            e.printStackTrace ();
            return "Unknown Server IP";
        }
    }

    private String sha1(String msg){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            //MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(msg.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
