package com.exam.service;

import com.exam.entity.Login;
import com.exam.vo.LoginResultVO;

/**
 * 登录服务接口
 * 定义登录相关的业务逻辑方法
 */
public interface LoginService {

    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return 管理员实体
     */
    // 保留原有方法，确保兼容性
    // public Admin adminLogin(Integer username, String password);

    /**
     * 教师登录
     * @param username 用户名
     * @param password 密码
     * @return 教师实体
     */
    // 保留原有方法，确保兼容性
    // public Teacher teacherLogin(Integer username, String password);

    /**
     * 学生登录
     * @param username 用户名
     * @param password 密码
     * @return 学生实体
     */
    // 保留原有方法，确保兼容性
    // public Student studentLogin(Integer username, String password);
    
    /**
     * 统一登录方法
     * @param login 登录请求参数
     * @return 登录结果VO
     */
    public LoginResultVO login(Login login);
}
