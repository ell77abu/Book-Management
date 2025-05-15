package com.example.SpringBoot.mapper;

import com.example.SpringBoot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE employee_id = #{employeeId}")
    User findByEmployeeId(String employeeId);
    
    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(username, password, real_name, employee_id, gender, age, role, created_at, updated_at) " +
            "VALUES(#{username}, #{password}, #{realName}, #{employeeId}, #{gender}, #{age}, #{role}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("<script>" +
    "UPDATE user " +
    "<set>" +
    "  <if test='realName != null'>real_name = #{realName},</if>" +
    "  <if test='gender != null'>gender = #{gender},</if>" +
    "  <if test='age != null'>age = #{age},</if>" +
    "  updated_at = #{updatedAt}" +
    "</set>" +
    "WHERE id = #{id}" +
    "</script>")
    int update(User user);
}

