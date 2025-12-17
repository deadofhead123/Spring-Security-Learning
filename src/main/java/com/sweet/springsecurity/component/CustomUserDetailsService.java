package com.sweet.springsecurity.component;

import com.sweet.springsecurity.entity.UserEntity;
import com.sweet.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity existingUser = userRepository.findByUsername(username);
        if(existingUser == null){
            throw new UsernameNotFoundException("Username = " + username + " not found!");
        }
        return existingUser;
    }
}
