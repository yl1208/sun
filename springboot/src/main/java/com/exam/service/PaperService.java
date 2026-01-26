package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;

import java.util.List;

public interface PaperService {

    /**
     * 查询所有试卷-题目关联
     * @return 关联列表
     */
    List<PaperManage> findAll();

    /**
     * 根据试卷ID查询关联的题目
     * @param paperId 试卷ID
     * @return 关联列表
     */
    List<PaperManage> findById(Integer paperId);

    /**
     * 添加试卷-题目关联
     * @param paperManage 关联对象
     * @return 添加结果
     */
    int add(PaperManage paperManage);

    /**
     * 根据试卷ID删除所有关联的题目
     * @param paperId 试卷ID
     * @return 删除结果
     */
    int deleteByPaperId(Integer paperId);

    /**
     * 根据试卷ID和题目类型删除关联的题目
     * @param paperId 试卷ID
     * @param questionType 题目类型
     * @return 删除结果
     */
    int deleteByPaperIdAndType(Integer paperId, Integer questionType);

    /**
     * 批量添加试卷-题目关联
     * @param paperManages 关联列表
     * @return 添加结果
     */
    int batchAdd(List<PaperManage> paperManages);
}
