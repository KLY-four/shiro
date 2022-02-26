package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class RoleEntity {

    private Long id;

    private String roleName;

    private List<PermisEntity> permis;
}
