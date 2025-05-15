package com.example.SpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBoot.common.MD5Util;
import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.User;
import com.example.SpringBoot.mapper.UserMapper;
import com.example.SpringBoot.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Date;



@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public Result<User> login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return Result.error("1001", "用户不存在");
        }
        
        // 对输入的密码进行MD5加密后再比较
        String encryptedPassword = MD5Util.encrypt(password);
        if (!encryptedPassword.equals(user.getPassword())) {
            return Result.error("1002", "密码错误");
        }
        
        // 登录成功，不返回密码
        user.setPassword(null);
        return Result.success(user);
    }
    
    @Override
    public Result<User> register(User user, Long operatorId) {
        // 检查操作者是否为超级管理员
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("1003", "操作者不存在");
        }
        
        if (operator.getRole() != 0) {
            return Result.error("1004", "权限不足，只有超级管理员可以注册新用户");
        }
        
        // 检查用户名是否已存在
        User existUser = userMapper.findByUsername(user.getUsername());
        if (existUser != null) {
            return Result.error("1005", "用户名已存在");
        }
        
        // 检查工号是否已存在
        existUser = userMapper.findByEmployeeId(user.getEmployeeId());
        if (existUser != null) {
            return Result.error("1006", "工号已存在");
        }
        
        // 设置默认值
        if (user.getRole() == null) {
            user.setRole(1); // 默认为普通管理员
        }
        
        // 密码加密
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        
        // 设置创建时间和更新时间
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        
        // 保存用户
        userMapper.insert(user);
        
        // 不返回密码
        user.setPassword(null);
        return Result.success(user);
    }
    
    @Override
    public Result<User> getUserInfo(Long userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            return Result.error("1007", "用户不存在");
        }
        
        // 不返回密码
        user.setPassword(null);
        return Result.success(user);
    }
    
    // 获取所有用户列表（仅超管）
    @Override
    public Result<List<User>> getUserList(Long operatorId) {
        // 检查操作者是否为超级管理员
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("1008", "操作者不存在");
        }
        
        if (operator.getRole() != 0) {
            return Result.error("1009", "权限不足，只有超级管理员可以查看所有用户");
        }
        
        List<User> users = userMapper.findAll();
        // 不返回密码
        for (User user : users) {
            user.setPassword(null);
        }
        
        return Result.success(users);
    }
    // 登录
    // 用户登录

    // 注册新用户（仅超管）

    // 获取自己的用户信息

    
    // 更新用户信息
    @Override
    public Result<User> editUserInfo(Map<String, Object> map, Long userId) {
        User existUser = userMapper.findById(userId);
        if (existUser == null) {
            return Result.error("1010", "用户不存在");
        }
        if (map.containsKey("realName")) {
            existUser.setRealName((String) map.get("realName"));
        }
        if (map.containsKey("gender")) {
            existUser.setGender((String) map.get("gender"));
        }
        if (map.containsKey("age")) {
            existUser.setAge((Integer) map.get("age"));
        }
    
        existUser.setUpdatedAt(new Date());
        userMapper.update(existUser);
    
        return Result.success(existUser);
    }
    // 删除用户（仅超管）
}
