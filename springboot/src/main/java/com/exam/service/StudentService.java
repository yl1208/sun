package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;

import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {

    /**
     * 分页查询所有学生
     */
    IPage<Student> findAll(Page page);

    /**
     * 根据 ID 查询学生
     */
    Student findById(Integer studentId);

    /**
     * 删除学生
     */
    int deleteById(Integer studentId);

    /**
     * 更新学生信息
     */
    int update(Student student);

    /**
     * 更新学生密码
     */
    int updatePwd(Student student);

    /**
     * 新增学生
     */
    int add(Student student);
}
