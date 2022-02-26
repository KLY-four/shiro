package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import com.example.demo.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int insertUser(UserEntity entity);

    UserEntity selectUser(String username);

    UserRoleVo selectUserRoles(String username);
}
