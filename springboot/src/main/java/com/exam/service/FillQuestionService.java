package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;

import java.util.List;

public interface FillQuestionService {

    /**
     * 根据试卷ID查询填空题
     * @param paperId 试卷ID
     * @return 填空题列表
     */
    List<FillQuestion> findByIdAndType(Integer paperId);

    /**
     * 分页查询所有填空题
     * @param page 分页对象
     * @return 分页结果
     */
    IPage<FillQuestion> findAll(Page<FillQuestion> page);

    /**
     * 查询最后一条记录的questionId
     * @return FillQuestion
     */
    FillQuestion findOnlyQuestionId();

    /**
     * 添加填空题
     * @param fillQuestion 填空题对象
     * @return 添加结果
     */
    int add(FillQuestion fillQuestion);

    /**
     * 随机获取指定科目和数量的填空题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 填空题ID列表
     */
    List<Integer> findBySubject(String subject, Integer pageNo);

    /**
     * 根据ID查询填空题
     * @param questionId 题目ID
     * @return 填空题对象
     */
    FillQuestion findById(Integer questionId);

    /**
     * 更新填空题
     * @param fillQuestion 填空题对象
     * @return 更新结果
     */
    int update(FillQuestion fillQuestion);

    /**
     * 删除填空题
     * @param questionId 题目ID
     * @return 删除结果
     */
    int delete(Integer questionId);
}
