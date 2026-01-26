package com.exam.service;

import com.exam.entity.PaperManage;

import java.util.List;

/**
 * 组卷服务接口
 * 用于定义人工组卷和规则组卷的方法
 */
public interface PaperComposeService {

    /**
     * 人工组卷
     * @param paperManages 试卷-题目关联列表
     * @return 组卷结果
     */
    int manualCompose(List<PaperManage> paperManages);

    /**
     * 规则组卷
     * @param paperId 试卷ID
     * @param subject 科目
     * @param multiCount 选择题数量
     * @param fillCount 填空题数量
     * @param judgeCount 判断题数量
     * @return 组卷结果
     */
    int ruleCompose(Integer paperId, String subject, Integer multiCount, Integer fillCount, Integer judgeCount);
}
