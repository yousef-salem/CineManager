package com.youssef.CineManager.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@RequestMapping("/api/movies/demo")
@CrossOrigin("*")
public class DemoController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> userAccess() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "hello I am user");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminAccess() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "hello I am admin");
        return ResponseEntity.ok(map);
    }

}