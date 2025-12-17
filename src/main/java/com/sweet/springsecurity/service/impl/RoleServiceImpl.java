package com.sweet.springsecurity.service.impl;

import com.sweet.springsecurity.entity.RoleEntity;
import com.sweet.springsecurity.repository.RoleRepository;
import com.sweet.springsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleEntity findById(Long id) {
        return roleRepository.findById(id).get();
    }
}
