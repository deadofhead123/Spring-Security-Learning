package com.sweet.springsecurity.dto.response;

import com.sweet.springsecurity.entity.RoleEntity;
import lombok.Data;

@Data
public class RegisterResponse {
    Long id;
    String username;
    RoleEntity role;
}
