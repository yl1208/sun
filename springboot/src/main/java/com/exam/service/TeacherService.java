package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Teacher;

import java.util.List;

/**
 * 教师服务接口
 */
public interface TeacherService {

    /**
     * 分页查询所有教师
     */
    IPage<Teacher> findAll(Page page);

    /**
     * 查询所有教师（不分页）
     */
    List<Teacher> findAll();

    /**
     * 根据 ID 查询教师
     */
    Teacher findById(Integer teacherId);

    /**
     * 删除教师
     */
    int deleteById(Integer teacherId);

    /**
     * 更新教师信息
     */
    int update(Teacher teacher);

    /**
     * 新增教师
     */
    int add(Teacher teacher);
}
