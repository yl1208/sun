package com.exam.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;
import com.exam.mapper.MultiQuestionMapper;
import com.exam.service.MultiQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiQuestionServiceImpl implements MultiQuestionService {

    @Autowired
    private MultiQuestionMapper multiQuestionMapper;

    /**
     * 根据试卷ID查询选择题
     * @param paperId 试卷ID
     * @return 选择题列表
     */
    @Override
    public List<MultiQuestion> findByIdAndType(Integer paperId) {
        return multiQuestionMapper.findByIdAndType(paperId);
    }

    /**
     * 分页查询所有选择题
     * @param page 分页对象
     * @return 分页结果
     */
    @Override
    public IPage<MultiQuestion> findAll(Page<MultiQuestion> page) {
        return multiQuestionMapper.findAll(page);
    }

    /**
     * 查询最后一条记录的questionId
     * @return MultiQuestion
     */
    @Override
    public MultiQuestion findOnlyQuestionId() {
        return multiQuestionMapper.findOnlyQuestionId();
    }

    /**
     * 添加选择题
     * @param multiQuestion 选择题对象
     * @return 添加结果
     */
    @Override
    public int add(MultiQuestion multiQuestion) {
        return multiQuestionMapper.add(multiQuestion);
    }

    /**
     * 随机获取指定科目和数量的选择题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 选择题ID列表
     */
    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return multiQuestionMapper.findBySubject(subject, pageNo);
    }

    /**
     * 根据ID查询选择题
     * @param questionId 题目ID
     * @return 选择题对象
     */
    @Override
    public MultiQuestion findById(Integer questionId) {
        return multiQuestionMapper.findById(questionId);
    }

    /**
     * 更新选择题
     * @param multiQuestion 选择题对象
     * @return 更新结果
     */
    @Override
    public int update(MultiQuestion multiQuestion) {
        return multiQuestionMapper.update(multiQuestion);
    }

    /**
     * 删除选择题
     * @param questionId 题目ID
     * @return 删除结果
     */
    @Override
    public int delete(Integer questionId) {
        return multiQuestionMapper.delete(questionId);
    }
}
