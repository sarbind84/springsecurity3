package com.inatializ.inatializ.controller;

import com.inatializ.inatializ.dto.AuthRequest;
import com.inatializ.inatializ.entity.UserInfo;
import com.inatializ.inatializ.util.JwtUtil;
import com.inatializ.inatializ.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class AuthenticateController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ProductService productService;

    @PostMapping("/login")
    public Map<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            Map<String, String> tokenFormat = new HashMap<>();
            String token = jwtUtil.generateToken(authRequest.getUsername());
            tokenFormat.put("token", token);
            return tokenFormat;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }

    }


    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return productService.addUser(userInfo);
    }
}
