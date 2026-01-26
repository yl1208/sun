package com.exam.controller;

import com.exam.Result.ApiResult;
import com.exam.Result.ApiResultHandler;
import com.exam.entity.*;
import com.exam.service.serviceimpl.FillQuestionServiceImpl;
import com.exam.service.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.service.serviceimpl.MultiQuestionServiceImpl;
import com.exam.service.serviceimpl.PaperComposeServiceImpl;
import com.exam.service.serviceimpl.PaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 试卷控制器
 */
@RestController
public class PaperController {

    @Autowired
    private PaperServiceImpl paperService;

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @Autowired
    private PaperComposeServiceImpl paperComposeService;

    /**
     * 查询所有试卷-题目关联
     * @return ApiResult
     */
    @GetMapping("/papers")
    public ApiResult<PaperManage> findAll() {
       return ApiResultHandler.buildApiResult(200,"请求成功",paperService.findAll());
    }

    /**
     * 查询试卷详情（包含所有题型的题目）
     * @param paperId 试卷ID
     * @return Map
     */
    @GetMapping("/paper/{paperId}")
    public Map<Integer, List<?>> findById(@PathVariable("paperId") Integer paperId) {
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 1
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        //Map封装
        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1,multiQuestionRes);
        map.put(2,fillQuestionsRes);
        map.put(3,judgeQuestionRes);
        return  map;
    }

    /**
     * 添加单个试卷-题目关联
     * @param paperManage 关联对象
     * @return ApiResult
     */
    @PostMapping("/paperManage")
    public ApiResult add(@RequestBody PaperManage paperManage) {
        int res = paperService.add(paperManage);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    /**
     * 人工组卷
     * @param paperManages 试卷-题目关联列表
     * @return ApiResult
     */
    @PostMapping("/manualCompose")
    public ApiResult manualCompose(@RequestBody List<PaperManage> paperManages) {
        int res = paperComposeService.manualCompose(paperManages);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"组卷成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"组卷失败",res);
    }

    /**
     * 规则组卷
     * @param paperId 试卷ID
     * @param subject 科目
     * @param multiCount 选择题数量
     * @param fillCount 填空题数量
     * @param judgeCount 判断题数量
     * @return ApiResult
     */
    @PostMapping("/ruleCompose")
    public ApiResult ruleCompose(@RequestParam Integer paperId, @RequestParam String subject,
                                 @RequestParam Integer multiCount, @RequestParam Integer fillCount,
                                 @RequestParam Integer judgeCount) {
        int res = paperComposeService.ruleCompose(paperId, subject, multiCount, fillCount, judgeCount);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"组卷成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"组卷失败",res);
    }

    /**
     * 根据试卷ID删除所有关联的题目
     * @param paperId 试卷ID
     * @return ApiResult
     */
    @DeleteMapping("/paperManage/{paperId}")
    public ApiResult deleteByPaperId(@PathVariable Integer paperId) {
        int res = paperService.deleteByPaperId(paperId);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"删除成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"删除失败",res);
    }
}
