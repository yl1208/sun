package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.entity.JudgeQuestion;
import com.exam.service.serviceimpl.JudgeQuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 判断问题控制器
 */
@RestController
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    /**
     * 添加判断题
     * @param judgeQuestion 判断题对象
     * @return ApiResult
     */
    @PostMapping("/judgeQuestion")
    public ApiResult add(@RequestBody JudgeQuestion judgeQuestion) {
        int res = judgeQuestionService.add(judgeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    /**
     * 查询最后一条记录的questionId
     * @return ApiResult
     */
    @GetMapping("/judgeQuestionId")
    public ApiResult findOnlyQuestionId() {
        JudgeQuestion res = judgeQuestionService.findOnlyQuestionId();
        return  ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 分页查询所有判断题
     * @param page 当前页码
     * @param size 每页大小
     * @return ApiResult
     */
    @GetMapping("/judgeQuestions/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<JudgeQuestion> judgeQuestionPage = new Page<>(page, size);
        IPage<JudgeQuestion> res = judgeQuestionService.findAll(judgeQuestionPage);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 根据ID查询判断题
     * @param questionId 题目ID
     * @return ApiResult
     */
    @GetMapping("/judgeQuestion/{questionId}")
    public ApiResult findById(@PathVariable Integer questionId) {
        JudgeQuestion res = judgeQuestionService.findById(questionId);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 更新判断题
     * @param judgeQuestion 判断题对象
     * @return ApiResult
     */
    @PutMapping("/judgeQuestion")
    public ApiResult update(@RequestBody JudgeQuestion judgeQuestion) {
        int res = judgeQuestionService.update(judgeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"更新成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"更新失败",res);
    }

    /**
     * 删除判断题
     * @param questionId 题目ID
     * @return ApiResult
     */
    @DeleteMapping("/judgeQuestion/{questionId}")
    public ApiResult delete(@PathVariable Integer questionId) {
        int res = judgeQuestionService.delete(questionId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"删除成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"删除失败",res);
    }
}
