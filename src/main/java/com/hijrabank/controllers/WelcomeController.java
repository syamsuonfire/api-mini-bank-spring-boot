package com.hijrabank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
public class WelcomeController {
    
    @GetMapping
    public String welcome() {
        return "Welcome to Hijra Bank";
    }

}
