package com.exam.controller;

import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.constant.LoginConstants;
import com.exam.entity.ExamAnswer;
import com.exam.service.serviceimpl.ExamServiceImpl;
import com.exam.vo.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 在线考试控制器
 * 用于提供在线考试和自动判分的RESTful API接口
 */
@RestController
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;

    /**
     * 开始考试
     * @param paperId 试卷ID
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @return ApiResult
     */
    @PostMapping("/exam/start")
    public ApiResult startExam(@RequestParam Integer paperId, HttpSession session) {
        // 从Session中获取当前登录用户信息
        LoginResultVO loginResult = (LoginResultVO) session.getAttribute(LoginConstants.SESSION_KEY_USER);
        if (loginResult == null) {
            return ApiResultHandler.buildApiResult(401, "用户未登录", null);
        }

        // 获取用户ID
        Integer userId = loginResult.getUserId();
        if (userId == null) {
            return ApiResultHandler.buildApiResult(401, "用户信息不完整", null);
        }

        // 开始考试，生成考试记录
        Integer recordId = examService.startExam(paperId, userId);
        return ApiResultHandler.buildApiResult(200, "开始考试成功", recordId);
    }

    /**
     * 提交试卷并自动判分
     * @param recordId 考试记录ID
     * @param answers 考生答题列表
     * @return ApiResult
     */
    @PostMapping("/exam/submit")
    public ApiResult submitExam(@RequestParam Integer recordId, @RequestBody List<ExamAnswer> answers) {
        // 提交试卷并自动判分
        Integer totalScore = examService.submitExam(recordId, answers);
        return ApiResultHandler.buildApiResult(200, "提交成功", totalScore);
    }

    /**
     * 获取考试结果
     * @param recordId 考试记录ID
     * @return ApiResult
     */
    @GetMapping("/exam/result/{recordId}")
    public ApiResult getExamResult(@PathVariable Integer recordId) {
        // 获取考试结果
        return ApiResultHandler.buildApiResult(200, "查询成功", examService.getExamResult(recordId));
    }
}
