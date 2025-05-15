package com.example.SpringBoot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.User;
import com.example.SpringBoot.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录
    @PostMapping("/login")
    public Result<User> login(@RequestBody Map<String, String> loginMap) {
        String username = loginMap.get("username");
        String password = loginMap.get("password");
        return userService.login(username, password);
    }

    // 注册（仅限超级管理员）
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user, @RequestHeader("userId") Long operatorId) {
        return userService.register(user, operatorId);
    }

    // 获取当前用户信息
    @GetMapping("/me")
    public Result<User> getUserInfo(@RequestHeader("userId") Long userId) {
        return userService.getUserInfo(userId);
    }

    // 获取所有用户列表（仅超级管理员可操作）
    @GetMapping("/list")
    public Result<List<User>> getUserList(@RequestHeader("userId") Long operatorId) {
        return userService.getUserList(operatorId);
    }

    // 修改自己的用户信息
    @PostMapping("/editInfo")
    public Result<User> editUserInfo(@RequestBody Map<String, Object> map, @RequestHeader("userId") Long userId) {
        return userService.editUserInfo(map, userId);
    }
    
}
