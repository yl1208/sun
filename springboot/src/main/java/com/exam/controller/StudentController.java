package com.exam.controller;

import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.entity.Student;
import com.exam.service.serviceimpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生控制器
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/list/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        // 创建Page对象，调用分页查询方法
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(page, size);
        return ApiResultHandler.buildApiResult(200, "请求成功", studentService.findAll(pageInfo));
    }

    @GetMapping("/{studentId}")
    public ApiResult findById(@PathVariable Integer studentId) {
        Student student = studentService.findById(studentId);
        if (student != null) {
            return ApiResultHandler.buildApiResult(200, "请求成功", student);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询失败，学生不存在", null);
        }
    }

    @PostMapping("/add")
    public ApiResult add(@RequestBody Student student) {
        int res = studentService.add(student);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "添加失败", res);
        }
    }

    @PutMapping("/update")
    public ApiResult update(@RequestBody Student student) {
        int res = studentService.update(student);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "更新成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "更新失败", res);
        }
    }

    @DeleteMapping("/{studentId}")
    public ApiResult deleteById(@PathVariable Integer studentId) {
        int res = studentService.deleteById(studentId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "删除成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "删除失败", res);
        }
    }


}
