package com.exam.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.mapper.FillQuestionMapper;
import com.exam.service.FillQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillQuestionServiceImpl implements FillQuestionService {

    @Autowired
    private FillQuestionMapper fillQuestionMapper;

    /**
     * 根据试卷ID查询填空题
     * @param paperId 试卷ID
     * @return 填空题列表
     */
    @Override
    public List<FillQuestion> findByIdAndType(Integer paperId) {
        return fillQuestionMapper.findByIdAndType(paperId);
    }

    /**
     * 分页查询所有填空题
     * @param page 分页对象
     * @return 分页结果
     */
    @Override
    public IPage<FillQuestion> findAll(Page<FillQuestion> page) {
        return fillQuestionMapper.findAll(page);
    }

    /**
     * 查询最后一条记录的questionId
     * @return FillQuestion
     */
    @Override
    public FillQuestion findOnlyQuestionId() {
        return fillQuestionMapper.findOnlyQuestionId();
    }

    /**
     * 添加填空题
     * @param fillQuestion 填空题对象
     * @return 添加结果
     */
    @Override
    public int add(FillQuestion fillQuestion) {
        return fillQuestionMapper.add(fillQuestion);
    }

    /**
     * 随机获取指定科目和数量的填空题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 填空题ID列表
     */
    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return fillQuestionMapper.findBySubject(subject, pageNo);
    }

    /**
     * 根据ID查询填空题
     * @param questionId 题目ID
     * @return 填空题对象
     */
    @Override
    public FillQuestion findById(Integer questionId) {
        return fillQuestionMapper.findById(questionId);
    }

    /**
     * 更新填空题
     * @param fillQuestion 填空题对象
     * @return 更新结果
     */
    @Override
    public int update(FillQuestion fillQuestion) {
        return fillQuestionMapper.update(fillQuestion);
    }

    /**
     * 删除填空题
     * @param questionId 题目ID
     * @return 删除结果
     */
    @Override
    public int delete(Integer questionId) {
        return fillQuestionMapper.delete(questionId);
    }
}
