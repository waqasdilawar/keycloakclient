package com.devgurupk.keycloakclient.controller;

import com.devgurupk.keycloakclient.security.AuthUserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/greeting")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Hello, world");
    }

    @GetMapping("/greeting-auth")
    public ResponseEntity<String> getAuth(@AuthenticationPrincipal AuthUserInfo securityUser){
        return ResponseEntity.ok("Hello, world");
    }
}
