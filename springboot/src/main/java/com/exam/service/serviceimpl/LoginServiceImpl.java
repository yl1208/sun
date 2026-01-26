package com.exam.service.serviceimpl;

import com.exam.constant.LoginConstants;
import com.exam.entity.Login;
import com.exam.entity.Admin;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.mapper.LoginMapper;
import com.exam.service.LoginService;
import com.exam.vo.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 登录服务实现类
 * 实现登录相关的业务逻辑
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 管理员登录（保留原有方法，确保兼容性）
     * @param username 用户名
     * @param password 密码
     * @return 管理员实体
     */
    public Admin adminLogin(Integer username, String password) {
        return loginMapper.adminLogin(username,password);
    }

    /**
     * 教师登录（保留原有方法，确保兼容性）
     * @param username 用户名
     * @param password 密码
     * @return 教师实体
     */
    public Teacher teacherLogin(Integer username, String password) {
        return loginMapper.teacherLogin(username,password);
    }

    /**
     * 学生登录（保留原有方法，确保兼容性）
     * @param username 用户名
     * @param password 密码
     * @return 学生实体
     */
    public Student studentLogin(Integer username, String password) {
        return loginMapper.studentLogin(username,password);
    }

    /**
     * 统一登录方法实现
     * @param login 登录请求参数
     * @return 登录结果VO
     */
    @Override
    public LoginResultVO login(Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        
        // 1. 尝试转换为Integer类型，因为数据库中用户ID是数字类型
        Integer userId = null;
        try {
            userId = Integer.parseInt(username);
            System.out.println("转换后的用户ID: " + userId);
        } catch (NumberFormatException e) {
            System.out.println("用户名不是数字格式: " + username);
            // 用户名不是数字格式，直接返回登录失败
            return new LoginResultVO(null, null, null, null, LoginConstants.LOGIN_FAILED);
        }
        
        // 2. 依次查询不同角色用户
        System.out.println("开始查询用户，用户名: " + username + "，密码: " + password);
        
        // 2.1 查询管理员
        Admin admin = loginMapper.adminLogin(userId, password);
        System.out.println("查询管理员结果: " + admin);
        if (admin != null) {
            return buildLoginResult(admin.getAdminId(), admin.getAdminName(), LoginConstants.ROLE_ADMIN, LoginConstants.ROLE_NAME_ADMIN, LoginConstants.LOGIN_SUCCESS);
        }
        
        // 2.2 查询教师
        Teacher teacher = loginMapper.teacherLogin(userId, password);
        System.out.println("查询教师结果: " + teacher);
        if (teacher != null) {
            return buildLoginResult(teacher.getTeacherId(), teacher.getTeacherName(), LoginConstants.ROLE_TEACHER, LoginConstants.ROLE_NAME_TEACHER, LoginConstants.LOGIN_SUCCESS);
        }
        
        // 2.3 查询学生
        Student student = loginMapper.studentLogin(userId, password);
        System.out.println("查询学生结果: " + student);
        if (student != null) {
            return buildLoginResult(student.getStudentId(), student.getStudentName(), LoginConstants.ROLE_STUDENT, LoginConstants.ROLE_NAME_STUDENT, LoginConstants.LOGIN_SUCCESS);
        }
        
        // 3. 所有角色都查询失败，返回登录失败
        System.out.println("所有角色查询失败");
        return new LoginResultVO(null, null, null, null, LoginConstants.LOGIN_FAILED);
    }
    
    /**
     * 构建登录结果VO
     * @param userId 用户ID
     * @param userName 用户名
     * @param role 角色标识
     * @param roleName 角色名称
     * @param status 登录状态
     * @return 登录结果VO
     */
    private LoginResultVO buildLoginResult(Integer userId, String userName, String role, String roleName, String status) {
        return new LoginResultVO(userId, userName, role, roleName, status);
    }
}
