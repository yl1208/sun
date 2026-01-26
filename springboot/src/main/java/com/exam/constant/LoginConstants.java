package com.exam.constant;

/**
 * 登录相关常量类
 * 定义系统中使用的角色常量、状态码等
 */
public class LoginConstants {
    
    // ------------------------ 角色常量 ------------------------
    public static final String ROLE_ADMIN = "0";       // 管理员角色标识
    public static final String ROLE_TEACHER = "1";     // 教师角色标识
    public static final String ROLE_STUDENT = "2";     // 学生角色标识
    
    // ------------------------ 角色名称 ------------------------
    public static final String ROLE_NAME_ADMIN = "管理员";  // 管理员角色名称
    public static final String ROLE_NAME_TEACHER = "教师";  // 教师角色名称
    public static final String ROLE_NAME_STUDENT = "学生";  // 学生角色名称
    
    // ------------------------ 登录状态 ------------------------
    public static final String LOGIN_SUCCESS = "success";  // 登录成功
    public static final String LOGIN_FAILED = "failed";    // 登录失败
    
    // ------------------------ 错误信息 ------------------------
    public static final String ERROR_USERNAME_PASSWORD = "用户名或密码错误";  // 用户名密码错误
    public static final String ERROR_USER_NOT_EXIST = "用户不存在";          // 用户不存在
    public static final String ERROR_USER_DISABLED = "用户已被禁用";        // 用户被禁用
    
    // ------------------------ Session键名 ------------------------
    public static final String SESSION_KEY_USER = "loginUser";  // Session中存储用户信息的键名
}
