package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.constant.LoginConstants;
import com.exam.entity.Score;
import com.exam.service.serviceimpl.ExamServiceImpl;
import com.exam.service.serviceimpl.ScoreServiceImpl;
import com.exam.vo.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 分数控制器
 */
@RestController
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @Autowired
    private ExamServiceImpl examService;

    /**
     * 查询所有学生成绩
     * @return ApiResult
     */
    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200,"查询所有学生成绩",res);
    }

    /**
     * 分页查询指定学生成绩
     * @param page 当前页码
     * @param size 每页大小
     * @param studentId 学生ID
     * @return ApiResult
     */
    @GetMapping("/score/{page}/{size}/{studentId}")
    public ApiResult findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") Integer studentId) {
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, studentId);
        return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
    }

    /**
     * 不分页查询指定学生成绩
     * @param studentId 学生ID
     * @return ApiResult
     */
    @GetMapping("/score/{studentId}")
        public ApiResult findById(@PathVariable("studentId") Integer studentId) {
        List<Score> res = scoreService.findById(studentId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "ID不存在", res);
        }
    }

    /**
     * 添加成绩记录
     * @param score 成绩对象
     * @return ApiResult
     */
    @PostMapping("/score")
    public ApiResult add(@RequestBody Score score) {
        int res = scoreService.add(score);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"成绩添加失败",res);
        }else {
            return ApiResultHandler.buildApiResult(200,"成绩添加成功",res);
        }
    }

    /**
     * 根据考试编号查询成绩
     * @param examCode 考试编号
     * @return ApiResult
     */
    @GetMapping("/scores/{examCode}")
    public ApiResult findByExamCode(@PathVariable("examCode") Integer examCode) {
        List<Score> scores = scoreService.findByExamCode(examCode);
        return ApiResultHandler.buildApiResult(200,"查询成功",scores);
    }

    /**
     * 查询个人考试成绩
     * @param session HttpSession对象，用于获取当前登录用户信息
     * @return ApiResult
     */
    @GetMapping("/score/my")
    public ApiResult getMyScores(HttpSession session) {
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

        // 查询当前用户的考试成绩
        return ApiResultHandler.buildApiResult(200, "查询成功", examService.getScoresByUserId(userId));
    }

    /**
     * 查询试卷的所有考试成绩
     * @param paperId 试卷ID
     * @return ApiResult
     */
    @GetMapping("/scores/paper/{paperId}")
    public ApiResult getScoresByPaperId(@PathVariable Integer paperId) {
        return ApiResultHandler.buildApiResult(200, "查询成功", examService.getScoresByPaperId(paperId));
    }

    /**
     * 查询考试成绩统计信息
     * @param paperId 试卷ID
     * @return ApiResult
     */
    @GetMapping("/score/statistic/{paperId}")
    public ApiResult statisticScore(@PathVariable Integer paperId) {
        return ApiResultHandler.buildApiResult(200, "查询成功", examService.statisticScore(paperId));
    }
}
