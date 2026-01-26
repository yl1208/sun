package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;

import java.util.List;

public interface MultiQuestionService {

    /**
     * 根据试卷ID查询选择题
     * @param paperId 试卷ID
     * @return 选择题列表
     */
    List<MultiQuestion> findByIdAndType(Integer paperId);

    /**
     * 分页查询所有选择题
     * @param page 分页对象
     * @return 分页结果
     */
    IPage<MultiQuestion> findAll(Page<MultiQuestion> page);

    /**
     * 查询最后一条记录的questionId
     * @return MultiQuestion
     */
    MultiQuestion findOnlyQuestionId();

    /**
     * 添加选择题
     * @param multiQuestion 选择题对象
     * @return 添加结果
     */
    int add(MultiQuestion multiQuestion);

    /**
     * 随机获取指定科目和数量的选择题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 选择题ID列表
     */
    List<Integer> findBySubject(String subject, Integer pageNo);

    /**
     * 根据ID查询选择题
     * @param questionId 题目ID
     * @return 选择题对象
     */
    MultiQuestion findById(Integer questionId);

    /**
     * 更新选择题
     * @param multiQuestion 选择题对象
     * @return 更新结果
     */
    int update(MultiQuestion multiQuestion);

    /**
     * 删除选择题
     * @param questionId 题目ID
     * @return 删除结果
     */
    int delete(Integer questionId);
}
