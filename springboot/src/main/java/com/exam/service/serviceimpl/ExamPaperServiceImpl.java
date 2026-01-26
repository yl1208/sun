package com.exam.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamPaper;
import com.exam.mapper.ExamPaperMapper;
import com.exam.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 试卷服务实现类
 * 用于实现试卷管理的业务逻辑
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private ExamPaperMapper examPaperMapper;

    /**
     * 分页查询所有试卷
     * @param page 分页对象
     * @return 分页结果
     */
    @Override
    public IPage<ExamPaper> findAll(Page<ExamPaper> page) {
        return examPaperMapper.findAll(page);
    }

    /**
     * 根据ID查询试卷
     * @param paperId 试卷ID
     * @return 试卷对象
     */
    @Override
    public ExamPaper findById(Integer paperId) {
        return examPaperMapper.findById(paperId);
    }

    /**
     * 新增试卷
     * @param examPaper 试卷对象
     * @return 新增结果
     */
    @Override
    public int add(ExamPaper examPaper) {
        return examPaperMapper.add(examPaper);
    }

    /**
     * 修改试卷
     * @param examPaper 试卷对象
     * @return 修改结果
     */
    @Override
    public int update(ExamPaper examPaper) {
        return examPaperMapper.update(examPaper);
    }

    /**
     * 删除试卷
     * @param paperId 试卷ID
     * @return 删除结果
     */
    @Override
    public int delete(Integer paperId) {
        return examPaperMapper.delete(paperId);
    }

    /**
     * 查询最后一条记录的paperId
     * @return 试卷ID
     */
    @Override
    public Integer findOnlyPaperId() {
        return examPaperMapper.findOnlyPaperId();
    }
}
