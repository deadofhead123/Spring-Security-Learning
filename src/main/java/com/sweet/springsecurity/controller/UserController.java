package com.sweet.springsecurity.controller;

import com.sweet.springsecurity.dto.ResponseDto;
import com.sweet.springsecurity.dto.request.LoginRequest;
import com.sweet.springsecurity.dto.request.RegisterRequest;
import com.sweet.springsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserController(
            AuthenticationManager authenticationManager,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        ResponseDto responseDto = new ResponseDto();

        try {
            responseDto.setData(userService.login(loginRequest));
            responseDto.setMessage("Login successfully");
            return ResponseEntity.ok(responseDto);
        }
        catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDto);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        ResponseDto responseDto = new ResponseDto();

        try {
            responseDto.setData(userService.register(registerRequest));
            responseDto.setMessage("successfully");
            return ResponseEntity.ok(responseDto);
        }
        catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }

    }
}

