package com.exam.service;

import com.exam.entity.Admin;
import java.util.List;

/**
 * 管理员服务接口
 */
public interface AdminService {

    /**
     * 查询所有管理员
     */
    List<Admin> findAll();

    /**
     * 根据 ID 查询管理员
     */
    Admin findById(Integer adminId);

    /**
     * 删除管理员
     */
    int deleteById(int adminId);

    /**
     * 更新管理员信息
     */
    int update(Admin admin);

    /**
     * 新增管理员
     */
    int add(Admin admin);
}
