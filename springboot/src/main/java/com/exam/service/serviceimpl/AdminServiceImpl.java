package com.exam.service.serviceimpl;

import com.exam.entity.Admin;
import com.exam.mapper.AdminMapper;
import com.exam.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员业务层实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询所有管理员
     */
    @Override
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    /**
     * 根据 ID 查询管理员
     */
    @Override
    public Admin findById(Integer adminId) {
        return adminMapper.findById(adminId);
    }

    /**
     * 删除管理员
     */
    @Override
    public int deleteById(int adminId) {
        return adminMapper.deleteById(adminId);
    }

    /**
     * 更新管理员信息
     */
    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    /**
     * 添加管理员
     */
    @Override
    public int add(Admin admin) {
        return adminMapper.add(admin);
    }
}
