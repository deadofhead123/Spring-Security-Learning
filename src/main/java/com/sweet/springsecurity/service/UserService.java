package com.sweet.springsecurity.service;

import com.sweet.springsecurity.dto.request.RegisterRequest;
import com.sweet.springsecurity.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);
}
