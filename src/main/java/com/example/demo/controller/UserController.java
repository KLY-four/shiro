package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save/user")
    public String saveUser(UserEntity entity){
        userService.insertUser(entity);
         return "redirect:/login";
     }

    @RequestMapping("/jd")
    @RequiresRoles("admin")
    public String add() {
        return "redirect:/toJd";
     }
}
