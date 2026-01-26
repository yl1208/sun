package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamPaper;

/**
 * 试卷服务接口
 * 用于定义试卷管理的业务逻辑方法
 */
public interface ExamPaperService {

    /**
     * 分页查询所有试卷
     * @param page 分页对象
     * @return 分页结果
     */
    IPage<ExamPaper> findAll(Page<ExamPaper> page);

    /**
     * 根据ID查询试卷
     * @param paperId 试卷ID
     * @return 试卷对象
     */
    ExamPaper findById(Integer paperId);

    /**
     * 新增试卷
     * @param examPaper 试卷对象
     * @return 新增结果
     */
    int add(ExamPaper examPaper);

    /**
     * 修改试卷
     * @param examPaper 试卷对象
     * @return 修改结果
     */
    int update(ExamPaper examPaper);

    /**
     * 删除试卷
     * @param paperId 试卷ID
     * @return 删除结果
     */
    int delete(Integer paperId);

    /**
     * 查询最后一条记录的paperId
     * @return 试卷ID
     */
    Integer findOnlyPaperId();
}
