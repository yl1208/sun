package com.exam.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Teacher;
import com.exam.mapper.TeacherMapper;
import com.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师业务层实现类
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 分页查询所有老师
     */
    @Override
    public IPage<Teacher> findAll(Page page) {
        return teacherMapper.findAll(page);
    }

    /**
     * 查询所有老师（不分页）
     */
    @Override
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }

    /**
     * 根据 ID 查询
     */
    @Override
    public Teacher findById(Integer teacherId) {
        return teacherMapper.findById(teacherId);
    }

    /**
     * 删除老师
     */
    @Override
    public int deleteById(Integer teacherId) {
        return teacherMapper.deleteById(teacherId);
    }

    /**
     * 更新老师信息
     */
    @Override
    public int update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    /**
     * 新增老师
     */
    @Override
    public int add(Teacher teacher) {
        return teacherMapper.add(teacher);
    }
}
