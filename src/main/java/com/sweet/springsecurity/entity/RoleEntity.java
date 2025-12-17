package com.sweet.springsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", length = 45)
    private String code;

    @Column(name = "name", length = 45)
    private String name;


}