package com.example.SpringBoot.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String realName;
    private String employeeId;
    private String gender;
    private Integer age;
    private Integer role; // 0: 超级管理员, 1: 普通管理员
    private Date createdAt;
    private Date updatedAt;
}
