package com.exam.service.serviceimpl;

import com.exam.entity.PaperManage;
import com.exam.service.PaperComposeService;
import com.exam.service.PaperService;
import com.exam.service.MultiQuestionService;
import com.exam.service.FillQuestionService;
import com.exam.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 组卷服务实现类
 * 用于实现人工组卷和规则组卷的功能
 */
@Service
public class PaperComposeServiceImpl implements PaperComposeService {

    @Autowired
    private PaperService paperService;

    @Autowired
    private MultiQuestionService multiQuestionService;

    @Autowired
    private FillQuestionService fillQuestionService;

    @Autowired
    private JudgeQuestionService judgeQuestionService;

    /**
     * 人工组卷
     * @param paperManages 试卷-题目关联列表
     * @return 组卷结果
     */
    @Override
    public int manualCompose(List<PaperManage> paperManages) {
        if (paperManages == null || paperManages.isEmpty()) {
            return 0;
        }

        // 获取试卷ID
        Integer paperId = paperManages.get(0).getPaperId();
        // 先删除该试卷原有的所有题目关联
        paperService.deleteByPaperId(paperId);
        // 批量添加新的题目关联
        return paperService.batchAdd(paperManages);
    }

    /**
     * 规则组卷
     * @param paperId 试卷ID
     * @param subject 科目
     * @param multiCount 选择题数量
     * @param fillCount 填空题数量
     * @param judgeCount 判断题数量
     * @return 组卷结果
     */
    @Override
    public int ruleCompose(Integer paperId, String subject, Integer multiCount, Integer fillCount, Integer judgeCount) {
        // 先删除该试卷原有的所有题目关联
        paperService.deleteByPaperId(paperId);

        List<PaperManage> paperManages = new ArrayList<>();

        // 添加选择题
        if (multiCount > 0) {
            List<Integer> multiQuestionIds = multiQuestionService.findBySubject(subject, multiCount);
            for (Integer questionId : multiQuestionIds) {
                paperManages.add(new PaperManage(paperId, 1, questionId));
            }
        }

        // 添加填空题
        if (fillCount > 0) {
            List<Integer> fillQuestionIds = fillQuestionService.findBySubject(subject, fillCount);
            for (Integer questionId : fillQuestionIds) {
                paperManages.add(new PaperManage(paperId, 2, questionId));
            }
        }

        // 添加判断题
        if (judgeCount > 0) {
            List<Integer> judgeQuestionIds = judgeQuestionService.findBySubject(subject, judgeCount);
            for (Integer questionId : judgeQuestionIds) {
                paperManages.add(new PaperManage(paperId, 3, questionId));
            }
        }

        // 批量添加题目关联
        return paperService.batchAdd(paperManages);
    }
}
