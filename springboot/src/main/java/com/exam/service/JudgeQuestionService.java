package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeQuestion;

import java.util.List;

public interface JudgeQuestionService {

    /**
     * 根据试卷ID查询判断题
     * @param paperId 试卷ID
     * @return 判断题列表
     */
    List<JudgeQuestion> findByIdAndType(Integer paperId);

    /**
     * 分页查询所有判断题
     * @param page 分页对象
     * @return 分页结果
     */
    IPage<JudgeQuestion> findAll(Page<JudgeQuestion> page);

    /**
     * 查询最后一条记录的questionId
     * @return JudgeQuestion
     */
    JudgeQuestion findOnlyQuestionId();

    /**
     * 添加判断题
     * @param judgeQuestion 判断题对象
     * @return 添加结果
     */
    int add(JudgeQuestion judgeQuestion);

    /**
     * 随机获取指定科目和数量的判断题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 判断题ID列表
     */
    List<Integer> findBySubject(String subject, Integer pageNo);

    /**
     * 根据ID查询判断题
     * @param questionId 题目ID
     * @return 判断题对象
     */
    JudgeQuestion findById(Integer questionId);

    /**
     * 更新判断题
     * @param judgeQuestion 判断题对象
     * @return 更新结果
     */
    int update(JudgeQuestion judgeQuestion);

    /**
     * 删除判断题
     * @param questionId 题目ID
     * @return 删除结果
     */
    int delete(Integer questionId);
}
