package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.entity.FillQuestion;
import com.exam.service.serviceimpl.FillQuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 填空题控制类
 */
@RestController
public class FillQuestionController {

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    /**
     * 添加填空题
     * @param fillQuestion 填空题对象
     * @return ApiResult
     */
    @PostMapping("/fillQuestion")
    public ApiResult add(@RequestBody FillQuestion fillQuestion) {
        int res = fillQuestionService.add(fillQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    /**
     * 查询最后一条记录的questionId
     * @return ApiResult
     */
    @GetMapping("/fillQuestionId")
    public ApiResult findOnlyQuestionId() {
        FillQuestion res = fillQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 分页查询所有填空题
     * @param page 当前页码
     * @param size 每页大小
     * @return ApiResult
     */
    @GetMapping("/fillQuestions/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<FillQuestion> fillQuestionPage = new Page<>(page, size);
        IPage<FillQuestion> res = fillQuestionService.findAll(fillQuestionPage);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 根据ID查询填空题
     * @param questionId 题目ID
     * @return ApiResult
     */
    @GetMapping("/fillQuestion/{questionId}")
    public ApiResult findById(@PathVariable Integer questionId) {
        FillQuestion res = fillQuestionService.findById(questionId);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 更新填空题
     * @param fillQuestion 填空题对象
     * @return ApiResult
     */
    @PutMapping("/fillQuestion")
    public ApiResult update(@RequestBody FillQuestion fillQuestion) {
        int res = fillQuestionService.update(fillQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"更新成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"更新失败",res);
    }

    /**
     * 删除填空题
     * @param questionId 题目ID
     * @return ApiResult
     */
    @DeleteMapping("/fillQuestion/{questionId}")
    public ApiResult delete(@PathVariable Integer questionId) {
        int res = fillQuestionService.delete(questionId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"删除成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"删除失败",res);
    }
}
