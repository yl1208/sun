package com.exam.controller;

import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.entity.Teacher;
import com.exam.service.serviceimpl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 教师控制器
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @GetMapping("/list/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        // 创建Page对象，调用分页查询方法
        com.baomidou.mybatisplus.extension.plugins.pagination.Page pageInfo = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(page, size);
        return ApiResultHandler.buildApiResult(200, "请求成功", teacherService.findAll(pageInfo));
    }

    @GetMapping("/{teacherId}")
    public ApiResult findById(@PathVariable Integer teacherId) {
        Teacher teacher = teacherService.findById(teacherId);
        if (teacher != null) {
            return ApiResultHandler.buildApiResult(200, "请求成功", teacher);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询失败，教师不存在", null);
        }
    }

    @PostMapping("/add")
    public ApiResult add(@RequestBody Teacher teacher) {
        int res = teacherService.add(teacher);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "添加失败", res);
        }
    }

    @PutMapping("/update")
    public ApiResult update(@RequestBody Teacher teacher) {
        int res = teacherService.update(teacher);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "更新成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "更新失败", res);
        }
    }

    @DeleteMapping("/{teacherId}")
    public ApiResult deleteById(@PathVariable Integer teacherId) {
        int res = teacherService.deleteById(teacherId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "删除成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "删除失败", res);
        }
    }
}
