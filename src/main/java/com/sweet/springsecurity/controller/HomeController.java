package com.sweet.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("")
    public String defaultMethod(){
        return "Hello";
    }

    @GetMapping("/admin")
    public String adminMethod(){
        return "This is admin method";
    }

    @GetMapping("/user")
    public String userMethod(){
        return "This is user method";
    }

    @GetMapping("/other")
    public String otherMethod(){
        return "This is other method";
    }
}
