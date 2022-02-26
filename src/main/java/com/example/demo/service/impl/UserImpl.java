package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insertUser(UserEntity entity) {
        final String uuid = UUID.randomUUID().toString();
        final Md5Hash md5Hash = new Md5Hash(entity.getPassword(), uuid, 1024);
        entity.setPassword(md5Hash.toHex());
        entity.setSalt(uuid);
        return userDao.insertUser(entity);
    }

}
