package com.sweet.springsecurity.repository;

import com.sweet.springsecurity.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
