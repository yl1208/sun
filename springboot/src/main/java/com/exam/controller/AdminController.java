package com.exam.controller;

import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.entity.Admin;
import com.exam.service.serviceimpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员管理接口
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/list")
    public ApiResult findAll() {
        return ApiResultHandler.buildApiResult(200, "请求成功", adminService.findAll());
    }

    @GetMapping("/{adminId}")
    public ApiResult findById(@PathVariable Integer adminId) {
        Admin admin = adminService.findById(adminId);
        if (admin != null) {
            return ApiResultHandler.buildApiResult(200, "请求成功", admin);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询失败，管理员不存在", null);
        }
    }

    @PostMapping("/add")
    public ApiResult add(@RequestBody Admin admin) {
        int res = adminService.add(admin);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "添加失败", res);
        }
    }

    @PutMapping("/update")
    public ApiResult update(@RequestBody Admin admin) {
        int res = adminService.update(admin);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "更新成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "更新失败", res);
        }
    }

    @DeleteMapping("/{adminId}")
    public ApiResult delete(@PathVariable Integer adminId) {
        int res = adminService.deleteById(adminId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "删除成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "删除失败", res);
        }
    }
}
