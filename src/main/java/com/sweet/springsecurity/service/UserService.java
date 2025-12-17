package com.sweet.springsecurity.service;

import com.sweet.springsecurity.dto.request.LoginRequest;
import com.sweet.springsecurity.dto.request.RegisterRequest;
import com.sweet.springsecurity.dto.response.RegisterResponse;

public interface UserService {
    String login(LoginRequest loginRequest) throws Exception;
    RegisterResponse register(RegisterRequest registerRequest);
}
