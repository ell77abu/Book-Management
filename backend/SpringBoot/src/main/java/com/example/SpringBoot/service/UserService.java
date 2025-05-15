package com.example.SpringBoot.service;

import java.util.List;
import java.util.Map;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.User;

public interface UserService {

    // 用户登录
    Result<User> login(String username, String password);
    // 注册新用户（仅超管）
    Result<User> register(User user, Long operatorId);
    // 获取自己的用户信息
    Result<User> getUserInfo(Long userId);
    // 获取所有用户列表（仅超管）
    Result<List<User>> getUserList(Long operatorId);
    // 更新用户信息
    Result<User> editUserInfo(Map<String, Object> map, Long userId);
    // 删除用户（仅超管）
}
