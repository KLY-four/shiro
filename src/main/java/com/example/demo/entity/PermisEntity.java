package com.example.demo.entity;

import lombok.Data;

import javax.annotation.security.DenyAll;

@Data
public class PermisEntity {

    private Long id;

    private String permis;
}
