package com.exam.controller;

import com.exam.constant.LoginConstants;
import com.exam.entity.Login;
import com.exam.service.serviceimpl.LoginServiceImpl;
import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.vo.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 * 处理用户登录请求，包括参数校验、登录验证、Session管理等
 */
@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    /**
     * 用户登录接口
     * @param login 登录请求参数
     * @param session HttpSession对象，用于存储登录状态
     * @return ApiResult 登录结果
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login, HttpSession session) {
        try {
            // 1. 参数校验
            if (login == null || login.getUsername() == null || login.getPassword() == null) {
                return ApiResultHandler.buildApiResult(400, "用户名或密码不能为空", null);
            }

            // 2. 调用统一登录方法
            LoginResultVO loginResult = loginService.login(login);

            // 3. 处理登录结果
            if (LoginConstants.LOGIN_SUCCESS.equals(loginResult.getStatus())) {
                // 3.1 登录成功，存储用户信息到Session
                session.setAttribute(LoginConstants.SESSION_KEY_USER, loginResult);
                // 3.2 返回成功结果
                return ApiResultHandler.buildApiResult(200, "登录成功", loginResult);
            } else {
                // 3.3 登录失败，返回错误信息
                return ApiResultHandler.buildApiResult(400, LoginConstants.ERROR_USERNAME_PASSWORD, null);
            }
        } catch (Exception e) {
            // 4. 异常处理
            e.printStackTrace();
            return ApiResultHandler.buildApiResult(500, "登录过程中发生异常", null);
        }
    }
    
    /**
     * 用户登出接口
     * @param session HttpSession对象，用于移除登录状态
     * @return ApiResult 登出结果
     */
    @PostMapping("/logout")
    public ApiResult logout(HttpSession session) {
        try {
            // 移除Session中的用户信息
            session.removeAttribute(LoginConstants.SESSION_KEY_USER);
            return ApiResultHandler.buildApiResult(200, "登出成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResultHandler.buildApiResult(500, "登出过程中发生异常", null);
        }
    }
}
