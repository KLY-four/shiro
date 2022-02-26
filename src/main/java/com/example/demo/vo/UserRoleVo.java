package com.example.demo.vo;

import com.example.demo.entity.RoleEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserRoleVo implements Serializable {

    private Long id;

    private String username;

    List<RoleEntity> roles;
}
