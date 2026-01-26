package com.exam.entity;

/**
 * 登录实体类
 * 用于接收登录请求参数
 */
public class Login {
    private String username;   // 用户名，支持字符串格式，更灵活
    private String password;   // 密码

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
