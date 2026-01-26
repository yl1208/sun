package com.exam.service.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeQuestion;
import com.exam.mapper.JudgeQuestionMapper;
import com.exam.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeQuestionServiceImpl implements JudgeQuestionService {

    @Autowired
    private JudgeQuestionMapper judgeQuestionMapper;

    /**
     * 根据试卷ID查询判断题
     * @param paperId 试卷ID
     * @return 判断题列表
     */
    @Override
    public List<JudgeQuestion> findByIdAndType(Integer paperId) {
        return judgeQuestionMapper.findByIdAndType(paperId);
    }

    /**
     * 分页查询所有判断题
     * @param page 分页对象
     * @return 分页结果
     */
    @Override
    public IPage<JudgeQuestion> findAll(Page<JudgeQuestion> page) {
        return judgeQuestionMapper.findAll(page);
    }

    /**
     * 查询最后一条记录的questionId
     * @return JudgeQuestion
     */
    @Override
    public JudgeQuestion findOnlyQuestionId() {
        return judgeQuestionMapper.findOnlyQuestionId();
    }

    /**
     * 添加判断题
     * @param judgeQuestion 判断题对象
     * @return 添加结果
     */
    @Override
    public int add(JudgeQuestion judgeQuestion) {
        return judgeQuestionMapper.add(judgeQuestion);
    }

    /**
     * 随机获取指定科目和数量的判断题ID
     * @param subject 科目
     * @param pageNo 数量
     * @return 判断题ID列表
     */
    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return judgeQuestionMapper.findBySubject(subject, pageNo);
    }

    /**
     * 根据ID查询判断题
     * @param questionId 题目ID
     * @return 判断题对象
     */
    @Override
    public JudgeQuestion findById(Integer questionId) {
        return judgeQuestionMapper.findById(questionId);
    }

    /**
     * 更新判断题
     * @param judgeQuestion 判断题对象
     * @return 更新结果
     */
    @Override
    public int update(JudgeQuestion judgeQuestion) {
        return judgeQuestionMapper.update(judgeQuestion);
    }

    /**
     * 删除判断题
     * @param questionId 题目ID
     * @return 删除结果
     */
    @Override
    public int delete(Integer questionId) {
        return judgeQuestionMapper.delete(questionId);
    }
}
