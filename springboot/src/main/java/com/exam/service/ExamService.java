package com.exam.service;

import com.exam.entity.ExamAnswer;
import com.exam.entity.ExamRecord;

import java.util.List;
import java.util.Map;

/**
 * 在线考试服务接口
 * 用于定义在线考试和自动判分的方法
 */
public interface ExamService {

    /**
     * 开始考试
     * @param paperId 试卷ID
     * @param userId 考生ID
     * @return 考试记录ID
     */
    Integer startExam(Integer paperId, Integer userId);

    /**
     * 提交试卷并自动判分
     * @param recordId 考试记录ID
     * @param answers 考生答题列表
     * @return 考试总分
     */
    Integer submitExam(Integer recordId, List<ExamAnswer> answers);

    /**
     * 获取考试结果
     * @param recordId 考试记录ID
     * @return 考试结果，包含考试记录和答题详情
     */
    Map<String, Object> getExamResult(Integer recordId);

    /**
     * 查询用户的考试成绩
     * @param userId 用户ID
     * @return 考试记录列表
     */
    List<ExamRecord> getScoresByUserId(Integer userId);

    /**
     * 查询试卷的所有考试成绩
     * @param paperId 试卷ID
     * @return 考试记录列表
     */
    List<ExamRecord> getScoresByPaperId(Integer paperId);

    /**
     * 统计考试成绩（平均分、最高分、最低分）
     * @param paperId 试卷ID
     * @return 统计结果，包含平均分、最高分、最低分和参考人数
     */
    Map<String, Object> statisticScore(Integer paperId);
}
