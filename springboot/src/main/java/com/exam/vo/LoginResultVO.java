package com.exam.vo;

/**
 * 登录结果VO类
 * 用于统一返回登录结果，包含用户基本信息和角色标识
 */
public class LoginResultVO {
    private Integer userId;        // 用户ID
    private String userName;       // 用户名
    private String role;           // 角色标识（0:管理员, 1:教师, 2:学生）
    private String roleName;       // 角色名称
    private String status;         // 登录状态

    /**
     * 无参构造方法
     */
    public LoginResultVO() {
    }

    /**
     * 有参构造方法
     * @param userId 用户ID
     * @param userName 用户名
     * @param role 角色标识
     * @param roleName 角色名称
     * @param status 登录状态
     */
    public LoginResultVO(Integer userId, String userName, String role, String roleName, String status) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.roleName = roleName;
        this.status = status;
    }

    // ------------------------ getter和setter方法 ------------------------
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * toString方法，用于打印对象信息
     * @return 对象的字符串表示
     */
    @Override
    public String toString() {
        return "LoginResultVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", roleName='" + roleName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
