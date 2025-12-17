package com.sweet.springsecurity.service.impl;

import com.sweet.springsecurity.dto.request.LoginRequest;
import com.sweet.springsecurity.dto.request.RegisterRequest;
import com.sweet.springsecurity.dto.response.RegisterResponse;
import com.sweet.springsecurity.entity.UserEntity;
import com.sweet.springsecurity.repository.UserRepository;
import com.sweet.springsecurity.service.RoleService;
import com.sweet.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequest loginRequest) throws Exception {
        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            loginRequest.getPassword()
//                    )
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "Login successful for user: " + authentication.getName();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");

        } catch (DisabledException e) {
            throw new DisabledException("Account is disabled");

        } catch (LockedException e) {
            throw new LockedException("Account is locked");

        } catch (Exception e) {
            throw new Exception("An error occurred during authentication");
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.findByUsername(registerRequest.getUsername()) != null){
            throw new DataIntegrityViolationException("Username existed");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setRole(roleService.findById(registerRequest.getRoleId()));

        UserEntity savedUser = userRepository.save(newUser);

        if(savedUser != null){
            return modelMapper.map(savedUser, RegisterResponse.class);
        }
        return null;
    }
}
