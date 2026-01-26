package com.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamPaper;
import com.exam.service.serviceimpl.ExamPaperServiceImpl;
import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 试卷控制器
 * 用于实现试卷管理的RESTful API接口
 */
@RestController
public class ExamPaperController {

    @Autowired
    private ExamPaperServiceImpl examPaperService;

    /**
     * 分页查询所有试卷
     * @param page 当前页码
     * @param size 每页大小
     * @return ApiResult
     */
    @GetMapping("/papers/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<ExamPaper> examPaperPage = new Page<>(page, size);
        return ApiResultHandler.buildApiResult(200, "查询成功", examPaperService.findAll(examPaperPage));
    }

    /**
     * 根据ID查询试卷
     * @param paperId 试卷ID
     * @return ApiResult
     */
    @GetMapping("/paperInfo/{paperId}")
    public ApiResult findById(@PathVariable Integer paperId) {
        ExamPaper examPaper = examPaperService.findById(paperId);
        if (examPaper != null) {
            return ApiResultHandler.buildApiResult(200, "查询成功", examPaper);
        }
        return ApiResultHandler.buildApiResult(404, "试卷不存在", null);
    }

    /**
     * 新增试卷
     * @param examPaper 试卷对象
     * @return ApiResult
     */
    @PostMapping("/paper")
    public ApiResult add(@RequestBody ExamPaper examPaper) {
        examPaper.setCreateTime(new Date());
        int res = examPaperService.add(examPaper);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    /**
     * 修改试卷
     * @param examPaper 试卷对象
     * @return ApiResult
     */
    @PutMapping("/paper")
    public ApiResult update(@RequestBody ExamPaper examPaper) {
        int res = examPaperService.update(examPaper);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "更新成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "更新失败", res);
    }

    /**
     * 删除试卷
     * @param paperId 试卷ID
     * @return ApiResult
     */
    @DeleteMapping("/paper/{paperId}")
    public ApiResult delete(@PathVariable Integer paperId) {
        int res = examPaperService.delete(paperId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "删除成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "删除失败", res);
    }

    /**
     * 查询最后一条记录的paperId
     * @return ApiResult
     */
    @GetMapping("/paperId")
    public ApiResult findOnlyPaperId() {
        Integer res = examPaperService.findOnlyPaperId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }
}
