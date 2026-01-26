package com.exam.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;
import com.exam.mapper.StudentMapper;
import com.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生业务层实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询所有学生
     */
    @Override
    public IPage<Student> findAll(Page page) {
        return studentMapper.findAll(page);
    }

    /**
     * 根据 ID 查询学生
     */
    @Override
    public Student findById(Integer studentId) {
        return studentMapper.findById(studentId);
    }

    /**
     * 删除学生
     */
    @Override
    public int deleteById(Integer studentId) {
        return studentMapper.deleteById(studentId);
    }

    /**
     * 更新学生信息
     */
    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    /**
     * 更新学生密码
     */
    @Override
    public int updatePwd(Student student) {
        return studentMapper.updatePwd(student);
    }

    /**
     * 添加学生
     */
    @Override
    public int add(Student student) {
        return studentMapper.add(student);
    }
}
