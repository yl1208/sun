package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.entity.MultiQuestion;
import com.exam.service.serviceimpl.MultiQuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 选择题控制器
 */
@RestController
public class MultiQuestionController {

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    /**
     * 查询最后一条记录的questionId
     * @return ApiResult
     */
    @GetMapping("/multiQuestionId")
    public ApiResult findOnlyQuestion() {
        MultiQuestion res = multiQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 添加选择题
     * @param multiQuestion 选择题对象
     * @return ApiResult
     */
    @PostMapping("/MultiQuestion")
    public ApiResult add(@RequestBody MultiQuestion multiQuestion) {
        int res = multiQuestionService.add(multiQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    /**
     * 分页查询所有选择题
     * @param page 当前页码
     * @param size 每页大小
     * @return ApiResult
     */
    @GetMapping("/multiQuestions/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<MultiQuestion> multiQuestionPage = new Page<>(page, size);
        IPage<MultiQuestion> res = multiQuestionService.findAll(multiQuestionPage);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 根据ID查询选择题
     * @param questionId 题目ID
     * @return ApiResult
     */
    @GetMapping("/multiQuestion/{questionId}")
    public ApiResult findById(@PathVariable Integer questionId) {
        MultiQuestion res = multiQuestionService.findById(questionId);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    /**
     * 更新选择题
     * @param multiQuestion 选择题对象
     * @return ApiResult
     */
    @PutMapping("/multiQuestion")
    public ApiResult update(@RequestBody MultiQuestion multiQuestion) {
        int res = multiQuestionService.update(multiQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"更新成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"更新失败",res);
    }

    /**
     * 删除选择题
     * @param questionId 题目ID
     * @return ApiResult
     */
    @DeleteMapping("/multiQuestion/{questionId}")
    public ApiResult delete(@PathVariable Integer questionId) {
        int res = multiQuestionService.delete(questionId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"删除成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"删除失败",res);
    }
}
